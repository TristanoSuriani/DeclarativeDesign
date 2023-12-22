package nl.suriani.declarative_design.examples.football.usecase;

import nl.suriani.declarative_design.examples.football.Match;
import nl.suriani.declarative_design.examples.football.MatchEvent;

import java.util.List;

public class PlanNewMatchUseCase {
    private final MatchRepository matchRepository;

    public PlanNewMatchUseCase(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Response<Match, MatchEvent> plan(PlanNewMatchCommand command) {
        var maybeMatch = matchRepository.findById(command.matchID());

        var match = maybeMatch.orElseGet(() ->
                new Match.NewMatch(command.matchID(), command.dateTime(), command.name(),
                    command.homeTeamID(), command.awayTeamID()));

        var initialState = maybeMatch.orElse(new Match.NoMatch());
        var decider = getDecider(initialState);
        var events = decider.decide().apply(command, match);
        var newState = Deciders.applyAllEventsToState(match, events, decider.evolve());

        events.stream().findAny()
                .ifPresent(e -> matchRepository.save(newState));

        return new Response<>(newState, events);
    }

    private Decider<PlanNewMatchCommand, Match, MatchEvent> getDecider(Match initialState) {
        return new Decider<>(
                (startMatchCommand, match) -> {
                    if (initialState instanceof Match.NoMatch) {
                        return List.of(new MatchEvent.MatchPlanned(match));
                    }
                    return List.of();
                },
                (match, matchEvent) -> match,
                (match) -> false,
                () -> initialState);
    }
}

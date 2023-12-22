package nl.suriani.declarative_design.examples.football.usecase;

import nl.suriani.declarative_design.examples.football.Match;
import nl.suriani.declarative_design.examples.football.MatchEvent;

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
        var decider = Deciders.planNewMatch(initialState);
        var events = decider.decide().apply(command, match);
        var newState = Deciders.applyAllEventsToState(match, events, decider.evolve());

        if (!events.isEmpty()) {
            matchRepository.save(newState);
        }

        return new Response<>(newState, events);
    }
}

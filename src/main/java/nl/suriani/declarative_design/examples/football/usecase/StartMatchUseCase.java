package nl.suriani.declarative_design.examples.football.usecase;

import nl.suriani.declarative_design.examples.football.Match;
import nl.suriani.declarative_design.examples.football.MatchEvent;

import java.util.List;
import java.util.function.Function;

public class StartMatchUseCase {
    private MatchRepository matchRepository;

    private static final Function<Match, Match> MATCH_IS_NEW_OR_IN_PROGRESS =
            match -> {
                if (!(match instanceof Match.NewMatch) && !(match instanceof Match.MatchInProgress)) {
                    throw new UseCaseFailureException("Match is in invalid state: " + match.getClass().getSimpleName());
                }
                return match;
            };

    public StartMatchUseCase(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Response<Match, MatchEvent> start(StartMatchCommand command) {
        var maybeMatch = matchRepository
                .findById(command.matchID());

        var match = maybeMatch
                .map(MATCH_IS_NEW_OR_IN_PROGRESS)
                .orElseGet(Match.NoMatch::new);

        var decider = getDecider(match);
        var events = decider.decide().apply(command, match);
        var newState = Deciders.applyAllEventsToState(match, events, decider.evolve());

        events.stream()
                .findAny()
                .ifPresent(e -> matchRepository.save(newState));

        return new Response<>(newState, events);
    }

    Decider<StartMatchCommand, Match, MatchEvent> getDecider(Match initialState) {
        return new Decider<>(
                (startMatchCommand, match) -> {
                    if (initialState instanceof Match.NewMatch) {
                        var newMatch = (Match.NewMatch) match;
                        return List.of(new MatchEvent.MatchStarted(newMatch.matchID()));
                    }
                    return List.of();
                },
                (match, matchEvent) -> {
                    if (match instanceof Match.NewMatch) {
                        return ((Match.NewMatch) match).setInProgress();
                    }
                    return match;
                },
                (match) -> false,
                () -> initialState
        );
    }
}

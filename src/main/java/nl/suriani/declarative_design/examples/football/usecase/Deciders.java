package nl.suriani.declarative_design.examples.football.usecase;

import nl.suriani.declarative_design.examples.football.Match;
import nl.suriani.declarative_design.examples.football.MatchEvent;

import java.util.List;
import java.util.function.BiFunction;

public interface Deciders {
    static Decider<PlanNewMatchCommand, Match, MatchEvent> planNewMatch(Match initialState) {
        return new Decider<>(
                (startMatchCommand, match) -> {
                    if (initialState instanceof Match.NoMatch) {
                        return List.of(new MatchEvent.MatchPlanned(match));
                    }
                    return List.of();
                },
                (match, matchEvent) -> match,
                (match) -> false,
                () -> initialState
        );
    }

    static Decider<StartMatchCommand, Match, MatchEvent> startMatch(Match initialState) {
        return new Decider<>(
                (startMatchCommand, match) -> {
                    if (!(initialState instanceof Match.NewMatch)) {
                        return List.of();
                    }
                    var newMatch = (Match.NewMatch) match;
                    return List.of(new MatchEvent.MatchStarted(newMatch.matchID()));
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

    static <S, E> S applyAllEventsToState(S state, List<E> events, BiFunction<S, E, S> evolveFunction) {
        if (events.isEmpty()) {
            return state;
        }

        return applyAllEventsToState(
                evolveFunction.apply(state, events.get(0)),
                events.subList(1, events.size()),
                evolveFunction);
    }

}

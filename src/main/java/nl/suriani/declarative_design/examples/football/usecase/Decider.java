package nl.suriani.declarative_design.examples.football.usecase;

import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

public record Decider<C, S, E>(
        BiFunction<C, S, List<E>> decide,
        BiFunction<S, E, S> evolve,
        Predicate<S> isTerminal,
        Supplier<S> getInitialState
) {
    public Decider {
        Objects.requireNonNull(decide);
        Objects.requireNonNull(evolve);
        Objects.requireNonNull(isTerminal);
        Objects.requireNonNull(getInitialState);
    }
}

package nl.suriani.declarative_design.examples.football.usecase;

import java.util.Arrays;
import java.util.List;

public record Response<S, E>(S newState, List<E> events) {
    public static <S, E> Response<S, E> of(S newState, E... events) {
        return new Response<>(newState, Arrays.asList(events));
    }
}

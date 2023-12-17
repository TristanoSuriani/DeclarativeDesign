package nl.suriani.declarative_design;

import java.util.Objects;
import java.util.Optional;
import java.util.function.*;

public class Validation<T> {
    private final T value;
    private final boolean valid;
    private final String error;

    private Validation(T value) {
        this.value = Objects.requireNonNull(value, "Value is required");
        this.valid = true;
        this.error = null;
    }

    private Validation(T value, boolean valid, String error) {
        this.value = Objects.requireNonNull(value, "Value is required");
        this.valid = valid;
        this.error = error;
    }

    public static <T> Validation<T> of(T value) {
        return new Validation<>(value);
    }

    public T get() {
        return value;
    }

    public boolean test() {
        return valid;
    }

    public Validation<T> validate(Predicate<T> predicate) {
        if (valid) {
            return new Validation<>(value, predicate.test(value), null);
        }

        return this;
    }

    public Validation<T> validate(Predicate<T> predicate, Function<T, String> errorProvider) {
        if (valid) {
            return new Validation<>(value, predicate.test(value), errorProvider.apply(value));
        }

        return this;
    }

    public <T2> Validation<T2> map(Function<T, T2> fn) {
        Objects.requireNonNull(fn, "fn cannot be null");
        return new Validation<T2>(fn.apply(value), valid, error);
    }

    public <T2> Validation<T2> flatMap(Function<T, Validation<T2>> fn) {
        Objects.requireNonNull(fn, "fn cannot be null");
        return new Validation<T2>(fn.apply(value).get(), valid, error);
    }

    public void ifSuccess(Consumer<T> consumer) {
        if (valid) {
            consumer.accept(value);
        }
    }

    public void ifFailure(Consumer<T> consumer) {
        if (!valid) {
            consumer.accept(value);
        }
    }

    public <T2> T2 ifSuccessOrElseAndGet(Function<T, T2> ifSuccess, BiConsumer<T, String> errorConsumer, BiFunction<T, String, T2> ifFailure) {
        if (valid) {
            return ifSuccess.apply(value);
        }

        errorConsumer.accept(value, error);
        return ifFailure.apply(value, error);
    }

    public <E extends RuntimeException> void ifFailureThenThrow(BiFunction<T, String, E> exceptionProvider) {
        if (!valid) {
            throw exceptionProvider.apply(value, error);
        }
    }

    public Optional<String> maybeError() {
        return Optional.ofNullable(error);
    }
}

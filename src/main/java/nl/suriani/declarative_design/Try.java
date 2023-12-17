package nl.suriani.declarative_design;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Try<T> {
    private final Supplier<T> supplier;

    private Try(Supplier<T> supplier) {
        this.supplier = Objects.requireNonNull(supplier, "supplier can not be null");
    }

    public static <T> Try<T> of(Supplier<T> supplier) {
        return new Try<>(supplier);
    }

    public <T2> Try<T2> map(Function<T, T2> fn) {
        Objects.requireNonNull(fn, "fn cannot be null");
        return new Try<>(() -> {
            var t = this.supplier.get();
            return fn.apply(t);
        });
    }

    public Optional<T> ifFailureThen(Consumer<Exception> exceptionConsumer) {
        try {
            return Optional.ofNullable(supplier.get());
        } catch (Exception exception) {
            exceptionConsumer.accept(exception);
        }
        return Optional.empty();
    }

    public T ifFailureThenOrElse(Consumer<Exception> exceptionConsumer, T alternativeValue) {
        try {
            return supplier.get();
        } catch (Exception exception) {
            exceptionConsumer.accept(exception);
        }
        return alternativeValue;
    }
}

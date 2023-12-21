package nl.suriani.playereventkata.application.domain.shared;

public abstract class Value<T> {
    private final T value;

    public Value(T value) {
        Guard.isNotNull(value);
        this.value = value;
    }

    public T value() {
        return value;
    }
}

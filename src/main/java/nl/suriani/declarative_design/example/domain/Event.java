package nl.suriani.declarative_design.example.domain;

import java.time.LocalDateTime;

public abstract class Event {
    private final LocalDateTime createdAt;

    public Event(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Event() {
        this.createdAt = LocalDateTime.now();
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public <T extends Event> boolean isOfType(Class<T> clazz) {
        return this.getClass().equals(clazz);
    }

    public <T extends Event> T as(Class<T> clazz) {
        if (this.isOfType(clazz)) {
            return clazz.cast(this);
        } else {
            throw new IllegalArgumentException("Event cannot be cast to " + clazz.getName());
        }
    }

}

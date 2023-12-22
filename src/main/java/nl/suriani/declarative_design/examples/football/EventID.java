package nl.suriani.declarative_design.examples.football;


import java.util.UUID;

public class EventID extends Value<UUID> {
    public EventID(UUID value) {
        super(value);
    }

    public EventID() {
        super(UUID.randomUUID());
    }
}

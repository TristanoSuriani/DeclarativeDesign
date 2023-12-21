package nl.suriani.declarative_design.examples.football;

import nl.suriani.playereventkata.application.domain.shared.Value;

import java.util.UUID;

public class EventID extends Value<UUID> {
    public EventID(UUID value) {
        super(value);
    }

    public EventID() {
        super(UUID.randomUUID());
    }
}

package nl.suriani.playereventkata.application.domain.match;

import nl.suriani.playereventkata.application.domain.shared.Value;

import java.util.UUID;

public class MatchID extends Value<UUID> {
    public MatchID(UUID value) {
        super(value);
    }

    public MatchID() {
        super(UUID.randomUUID());
    }
}

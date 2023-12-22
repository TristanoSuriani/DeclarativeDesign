package nl.suriani.declarative_design.examples.football;


import java.util.UUID;

public class MatchID extends Value<UUID> {
    public MatchID(UUID value) {
        super(value);
    }

    public MatchID() {
        super(UUID.randomUUID());
    }
}

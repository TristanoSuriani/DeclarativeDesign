package nl.suriani.declarative_design.examples.football;


import java.util.UUID;

public class PlayerID extends Value<UUID> {
    public PlayerID(UUID value) {
        super(value);
    }
}

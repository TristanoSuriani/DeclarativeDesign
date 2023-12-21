package nl.suriani.declarative_design.examples.football;

import nl.suriani.playereventkata.application.domain.shared.Value;

import java.util.UUID;

public class PlayerID extends Value<UUID> {
    public PlayerID(UUID value) {
        super(value);
    }
}

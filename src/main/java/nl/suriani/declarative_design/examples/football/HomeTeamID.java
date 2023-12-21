package nl.suriani.declarative_design.examples.football;

import nl.suriani.playereventkata.application.domain.shared.Value;

import java.util.UUID;

public class TeamID extends Value<UUID> {
    public TeamID(UUID value) {
        super(value);
    }
}

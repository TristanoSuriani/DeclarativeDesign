package nl.suriani.declarative_design.examples.football;

import nl.suriani.playereventkata.application.domain.shared.Guard;

public record Player(PlayerID playerID, PlayerName name, boolean isEligible) {
    public Player {
        Guard.areNotNull(playerID, name);
    }
}

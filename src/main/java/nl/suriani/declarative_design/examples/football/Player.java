package nl.suriani.declarative_design.examples.football;


public record Player(PlayerID playerID, PlayerName name, boolean isEligible) {
    public Player {
        Guard.areNotNull(playerID, name);
    }
}

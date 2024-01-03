package nl.suriani.declarative_design.examples.rpsls;

import java.util.UUID;

public record Player(UUID id, String name, int score) {
    public Player {
        Guards.isNotNull(id);
        Guards.isNotNull(name);
        Guards.isZeroOrPositive(score);
    }

    Player incrementScore() {
        return new Player(id, name, score + 1);
    }
}

package nl.suriani.declarative_design.examples.rpsls;

import java.util.UUID;

public class GameDBEntity {

    private UUID id;
    private String player1Id;
    private String player2Id;
    private String player1Name;
    private String player2Name;
    private int player1Score;
    private int player2Score;
    private String player1Move;
    private String player2Move;
    private GameStatus status;
}

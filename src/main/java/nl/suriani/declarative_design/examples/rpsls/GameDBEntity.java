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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPlayer1Id() {
        return player1Id;
    }

    public void setPlayer1Id(String player1Id) {
        this.player1Id = player1Id;
    }

    public String getPlayer2Id() {
        return player2Id;
    }

    public void setPlayer2Id(String player2Id) {
        this.player2Id = player2Id;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }

    public String getPlayer1Move() {
        return player1Move;
    }

    public void setPlayer1Move(String player1Move) {
        this.player1Move = player1Move;
    }

    public String getPlayer2Move() {
        return player2Move;
    }

    public void setPlayer2Move(String player2Move) {
        this.player2Move = player2Move;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }
}

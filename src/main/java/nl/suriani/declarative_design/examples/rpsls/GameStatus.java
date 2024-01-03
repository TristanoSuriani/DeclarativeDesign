package nl.suriani.declarative_design.examples.rpsls;

public enum GameStatus {
    NEW,
    WAITING_FOR_BOTH_PLAYERS_MOVES,
    WAITING_FOR_PLAYER1_MOVE,
    WAITING_FOR_PLAYER2_MOVE,
    PLAYER1_WINS,
    PLAYER2_WINS,
    CANCELLED
}

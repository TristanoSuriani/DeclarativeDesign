package nl.suriani.declarative_design.examples.rpsls;

import java.util.UUID;

public class GameMapper {
    public Game GameDBEntityToGame(GameDBEntity entity) {
        return switch (entity.getStatus()) {
            case NEW -> toNewGame(entity);
            case WAITING_FOR_BOTH_PLAYERS_MOVES -> toWaitingForBothPlayersMoves(entity);
            case WAITING_FOR_PLAYER1_MOVE -> toWaitingForPlayer1Move(entity);
            case WAITING_FOR_PLAYER2_MOVE -> toWaitingForPlayer2Move(entity);
            case PLAYER1_WINS -> toPlayer1Wins(entity);
            case PLAYER2_WINS -> toPlayer2Wins(entity);
            case CANCELLED -> toCancelled(entity);
        };
    }

    private Game.NewGame toNewGame(GameDBEntity entity) {
        return new Game.NewGame(entity.getId(), new Player(entity.getId(), entity.getPlayer1Name(), entity.getPlayer1Score()));
    }

    private Game.InProgress.WaitingForBothPlayersMoves toWaitingForBothPlayersMoves(GameDBEntity entity) {
        return new Game.InProgress.WaitingForBothPlayersMoves(entity.getId(),
                new Player(UUID.fromString(entity.getPlayer1Id()), entity.getPlayer1Name(), entity.getPlayer1Score()),
                new Player(UUID.fromString(entity.getPlayer2Id()), entity.getPlayer2Name(), entity.getPlayer2Score()));
    }

    private Game.InProgress.WaitingForPlayer1Move toWaitingForPlayer1Move(GameDBEntity entity) {
        return new Game.InProgress.WaitingForPlayer1Move(entity.getId(),
                new Player(UUID.fromString(entity.getPlayer1Id()), entity.getPlayer1Name(), entity.getPlayer1Score()),
                new Player(UUID.fromString(entity.getPlayer2Id()), entity.getPlayer2Name(), entity.getPlayer2Score()),
                Move.valueOf(entity.getPlayer2Move()));
    }

    private Game.InProgress.WaitingForPlayer2Move toWaitingForPlayer2Move(GameDBEntity entity) {
        return new Game.InProgress.WaitingForPlayer2Move(entity.getId(),
                new Player(UUID.fromString(entity.getPlayer1Id()), entity.getPlayer1Name(), entity.getPlayer1Score()),
                new Player(UUID.fromString(entity.getPlayer2Id()), entity.getPlayer2Name(), entity.getPlayer2Score()),
                Move.valueOf(entity.getPlayer1Move()));
    }

    private Game.Terminated.Player1Wins toPlayer1Wins(GameDBEntity entity) {
        return new Game.Terminated.Player1Wins(entity.getId(),
                new Player(UUID.fromString(entity.getPlayer1Id()), entity.getPlayer1Name(), entity.getPlayer1Score()),
                new Player(UUID.fromString(entity.getPlayer2Id()), entity.getPlayer2Name(), entity.getPlayer2Score()));
    }

    private Game.Terminated.Player2Wins toPlayer2Wins(GameDBEntity entity) {
        return new Game.Terminated.Player2Wins(entity.getId(),
                new Player(UUID.fromString(entity.getPlayer1Id()), entity.getPlayer1Name(), entity.getPlayer1Score()),
                new Player(UUID.fromString(entity.getPlayer2Id()), entity.getPlayer2Name(), entity.getPlayer2Score()));
    }

    private Game.Terminated.GameCancelled toCancelled(GameDBEntity entity) {
        return new Game.Terminated.GameCancelled(entity.getId());
    }
}

package nl.suriani.declarative_design.examples.rpsls;

import java.util.UUID;

public sealed interface Game {

    static NewGame start(Player player1) {
        return new NewGame(UUID.randomUUID(), player1);
    }

    record NewGame(UUID id, Player player1) implements Game {
        Terminated.GameCancelled cancel() {
            return new Terminated.GameCancelled(id);
        }

        InProgress.WaitingForBothPlayersMoves setInProgress(Player player2) {
            return new InProgress.WaitingForBothPlayersMoves(id, player1, player2);
        }
    }

    sealed interface InProgress extends Game {
        record WaitingForBothPlayersMoves(UUID id, Player player1, Player player2) implements InProgress {
            Terminated.GameCancelled cancel() {
                return new Terminated.GameCancelled(id);
            }

            WaitingForPlayer2Move addPlayer1Move(Move move) {
                return new WaitingForPlayer2Move(id, player1, player2, move);
            }

            WaitingForPlayer1Move addPlayer2Move(Move move) {
                return new WaitingForPlayer1Move(id, player1, player2, move);
            }
        }

        record WaitingForPlayer1Move(UUID id, Player player1, Player player2, Move movePlayer2) implements InProgress {
            Terminated.GameCancelled cancel() {
                return new Terminated.GameCancelled(id);
            }

            Terminated.Player1Wins setAsPlayer1Wins() {
                return new Terminated.Player1Wins(id, player1, player2);
            }

            Terminated.Player2Wins setAsPlayer2Wins() {
                return new Terminated.Player2Wins(id, player1, player2);
            }
        }

        record WaitingForPlayer2Move(UUID id, Player player1, Player player2, Move movePlayer1) implements InProgress {
            Terminated.GameCancelled cancel() {
                return new Terminated.GameCancelled(id);
            }

            Terminated.Player1Wins setAsPlayer1Wins() {
                return new Terminated.Player1Wins(id, player1, player2);
            }

            Terminated.Player2Wins setAsPlayer2Wins() {
                return new Terminated.Player2Wins(id, player1, player2);
            }
        }
    }

    sealed interface Terminated extends Game {
        record Player1Wins(UUID id, Player player1, Player player2) implements Terminated {}

        record Player2Wins(UUID id, Player player1, Player player2) implements Terminated {}

        record GameCancelled(UUID id) implements Terminated {}
    }

}

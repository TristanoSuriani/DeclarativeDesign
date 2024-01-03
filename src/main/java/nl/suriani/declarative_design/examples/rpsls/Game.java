package nl.suriani.declarative_design.examples.rpsls;

import java.util.UUID;

public sealed interface Game {

    static NewGame start(Player player1) {
        return new NewGame(UUID.randomUUID(), player1);
    }

    record NewGame(UUID id, Player player1) implements Game {
        public NewGame {
            Guards.isNotNull(id);
            Guards.isNotNull(player1);
        }

        Terminated.GameCancelled cancel() {
            return new Terminated.GameCancelled(id);
        }

        InProgress.WaitingForBothPlayersMoves setInProgress(Player player2) {
            return new InProgress.WaitingForBothPlayersMoves(id, player1, player2);
        }
    }

    sealed interface InProgress extends Game {
        record WaitingForBothPlayersMoves(UUID id, Player player1, Player player2) implements InProgress {
            public WaitingForBothPlayersMoves {
                Guards.isNotNull(id);
                Guards.isNotNull(player1);
                Guards.isNotNull(player2);
            }

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
            public WaitingForPlayer1Move {
                Guards.isNotNull(id);
                Guards.isNotNull(player1);
                Guards.isNotNull(player2);
            }

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
            public WaitingForPlayer2Move {
                Guards.isNotNull(id);
                Guards.isNotNull(player1);
                Guards.isNotNull(player2);
            }

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
        record Player1Wins(UUID id, Player player1, Player player2) implements Terminated {
            public Player1Wins {
                Guards.isNotNull(id);
                Guards.isNotNull(player1);
                Guards.isNotNull(player2);
            }
        }

        record Player2Wins(UUID id, Player player1, Player player2) implements Terminated {
            public Player2Wins {
                Guards.isNotNull(id);
                Guards.isNotNull(player1);
                Guards.isNotNull(player2);
            }
        }

        record GameCancelled(UUID id) implements Terminated {
            public GameCancelled {
                Guards.isNotNull(id);
            }
        }
    }

}

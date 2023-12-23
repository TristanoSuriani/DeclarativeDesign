package nl.suriani.declarative_design.examples.football.usecase;

public class UseCaseFailureException extends RuntimeException {
    public UseCaseFailureException(String message) {
        super(message);
    }

    public UseCaseFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public UseCaseFailureException(Throwable cause) {
        super(cause);
    }
}

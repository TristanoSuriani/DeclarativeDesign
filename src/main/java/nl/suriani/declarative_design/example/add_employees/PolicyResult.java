package nl.suriani.declarative_design.example.add_employees;

import java.util.Optional;

public record PolicyResult(Optional<String> error) {
    public static PolicyResult ok() {
        return new PolicyResult(Optional.empty());
    }

    public static PolicyResult error(String error) {
        return new PolicyResult(Optional.of(error));
    }
}

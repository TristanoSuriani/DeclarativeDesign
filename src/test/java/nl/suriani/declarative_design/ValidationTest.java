package nl.suriani.declarative_design;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {
    @Test
    void test() {
        var result = Validation.of("Hello world")
                .validate(s -> s.startsWith("Hello"))
                .validate(s -> s.endsWith("world"))
                .test();

        assertTrue(result);

        result = Validation.of("Hello world")
                .validate(s -> s.startsWith("Hello"))
                .map(String::toUpperCase)
                .validate(s -> s.endsWith("WORLD"))
                .test();

        assertTrue(result);

        Validation.of(Validation.of("Hello world"))
                .flatMap(s -> s)
                .validate(s -> s.startsWith("Hello"))
                .map(String::toUpperCase)
                .validate(s -> s.endsWith("WORLD"))
                .ifFailure(s -> {
                    // do something with s
                });

        assertTrue(result);

        result = Validation.of("Hello world")
                .map(s -> s.length())
                .validate(l -> l > 5)
                .test();

        assertTrue(result);
        
        var error = Validation.of(5)
                .validate(i -> i < 4, i -> "expected at least " + 4 + " but got " + 5)
                .maybeError()
                .get();

        assertEquals("expected at least 4 but got 5", error);
    }

    @Test
    void testIfFailureThenThrow() {
        Assertions.assertThrows(CustomException.class, () ->

                Validation.of(5)
                    .validate(i -> i < 4, i -> "expected at least " + 4 + " but got " + 5)
                        .ifFailureThenThrow((value, error) -> new CustomException(error)));
    }

    private class CustomException extends RuntimeException {

        public CustomException(String message) {
            super(message);
        }

        public CustomException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
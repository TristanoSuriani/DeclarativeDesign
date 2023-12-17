package nl.suriani.declarative_design;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TryTest {
    @Test
    void testTrySupply() {
        var result =
                Try.of(() -> "Hello World")
                        .map(String::toUpperCase)
                        .ifFailureThenOrElse(e -> System.out.println(e.getMessage()), "hallo");

        assertEquals("HELLO WORLD", result);

        result = Try.of(() -> "Hello World")
                .map(String::toUpperCase)
                .map(s -> {
                    if (true) {
                        throw new UnsupportedOperationException("no pineapple on pizza");
                    }
                    return s;
                })
                .ifFailureThenOrElse(e -> System.out.println(e.getMessage()), "hallo");

        assertEquals("hallo", result);
    }
}
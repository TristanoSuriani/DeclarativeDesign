package nl.suriani.declarative_design.examples.add_employees;

import nl.suriani.declarative_design.examples.add_employees.domain.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    @Test
    void testIsOfType() {
        var event = new SomeTestEvent(LocalDateTime.now());
        assertTrue(event.isOfType(SomeTestEvent.class));
    }

    private class SomeTestEvent extends Event {
        public SomeTestEvent(LocalDateTime createdAt) {
            super(createdAt);
        }
    }

}
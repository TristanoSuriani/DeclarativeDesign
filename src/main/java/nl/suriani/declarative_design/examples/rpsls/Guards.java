package nl.suriani.declarative_design.examples.rpsls;

import nl.suriani.declarative_design.examples.RequiredFieldIsMissingException;
import nl.suriani.declarative_design.examples.ValueOutOfRangeException;

public interface Guards {
    static void isNotNull(Object o) {
        if (o == null) {
            throw new RequiredFieldIsMissingException();
        }
    }

    static void isGreaterOrEquals(int n, int m) {
        if (n < m) {
            throw new ValueOutOfRangeException();
        }
    }

    static void isZeroOrPositive(int n) {
        isGreaterOrEquals(n, 0);
    }
}

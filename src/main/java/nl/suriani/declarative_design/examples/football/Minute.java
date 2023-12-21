package nl.suriani.declarative_design.examples.football;

import nl.suriani.playereventkata.application.domain.shared.Guard;
import nl.suriani.playereventkata.application.domain.shared.Value;

public class Minute extends Value<Integer> {
    public Minute(Integer value) {
        super(value);
        Guard.value(value)
                .isBetween(0)
                .and(135);
    }
}

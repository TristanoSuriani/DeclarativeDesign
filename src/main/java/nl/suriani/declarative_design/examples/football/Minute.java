package nl.suriani.declarative_design.examples.football;


public class Minute extends Value<Integer> {
    public Minute(Integer value) {
        super(value);
        Guard.value(value)
                .isBetween(0)
                .and(135);
    }
}

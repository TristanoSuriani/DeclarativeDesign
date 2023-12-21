package nl.suriani.declarative_design.examples.football;

import nl.suriani.playereventkata.application.domain.shared.Value;

public class MatchName extends Value<String> {
    public MatchName(String value) {
        super(value);
    }

    public MatchName() {
        super("to be defined");
    }
}

package nl.suriani.playereventkata.application.domain.match;

import nl.suriani.playereventkata.application.domain.shared.Value;

public class MatchName extends Value<String> {
    public MatchName(String value) {
        super(value);
    }

    public MatchName() {
        super("to be defined");
    }
}

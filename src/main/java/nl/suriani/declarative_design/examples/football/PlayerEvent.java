package nl.suriani.declarative_design.examples.football;

import nl.suriani.playereventkata.application.domain.shared.Guard;

import java.util.List;

public record PlayerEvent(EventID eventID,
                          PlayerID playerID,
                          PlayerName playerName,
                          EventType eventType,
                          Minute minute,
                          List<Note> notes) {

    public PlayerEvent {
        Guard.areNotNull(eventID, playerID, playerName, eventType, minute, notes);
    }
}

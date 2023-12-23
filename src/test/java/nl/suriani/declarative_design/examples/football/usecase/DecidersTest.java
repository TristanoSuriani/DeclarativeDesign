package nl.suriani.declarative_design.examples.football.usecase;

import nl.suriani.declarative_design.examples.football.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DecidersTest {

    @Test
    void planNewMatch_matchIsAlreadyPlanned_noEvents() {
        var match = new Match.NewMatch(
                new MatchID(UUID.randomUUID()),
                LocalDateTime.now().plusDays(13),
                new MatchName("Milan vs Ajax"),
                new HomeTeamID(UUID.randomUUID()),
                new AwayTeamID(UUID.randomUUID()));

        var decider = Deciders.planNewMatch(match);
        var command = new PlanNewMatchCommand(match.matchID(),
                match.dateTime(),
                match.name(),
                match.homeTeamID(),
                match.awayTeamID());

        var events = decider.decide().apply(command, match);
        assertTrue(events.isEmpty());
    }

    @Test
    void planNewMatch_matchIsPlanned() {
        var match = new Match.NewMatch(
                new MatchID(UUID.randomUUID()),
                LocalDateTime.now().plusDays(13),
                new MatchName("Milan vs Ajax"),
                new HomeTeamID(UUID.randomUUID()),
                new AwayTeamID(UUID.randomUUID()));

        var decider = Deciders.planNewMatch(new Match.NoMatch());
        var command = new PlanNewMatchCommand(match.matchID(),
                match.dateTime(),
                match.name(),
                match.homeTeamID(),
                match.awayTeamID());

        var events = decider.decide().apply(command, match);
        assertEquals(1, events.size());
        assertTrue(events.get(0) instanceof MatchEvent.NewMatchCreated);
        var newState = Deciders.applyAllEventsToState(match, events, decider.evolve());
        assertEquals(match, newState);
    }

    @Test
    void startMatch_matchIsAlreadyStarted_noEvents() {
        var match = new Match.MatchInProgress(
                new MatchID(UUID.randomUUID()),
                LocalDateTime.now().plusDays(13),
                new MatchName("Milan vs Ajax"),
                new HomeTeamID(UUID.randomUUID()),
                new AwayTeamID(UUID.randomUUID()),
                new Score());

        var decider = Deciders.startMatch(match);
        var command = new StartMatchCommand(match.matchID());

        var events = decider.decide().apply(command, match);
        assertTrue(events.isEmpty());
    }

    @Test
    void startMatch_matchInProgress() {
        var match = new Match.NewMatch(
                new MatchID(UUID.randomUUID()),
                LocalDateTime.now().plusDays(13),
                new MatchName("Milan vs Ajax"),
                new HomeTeamID(UUID.randomUUID()),
                new AwayTeamID(UUID.randomUUID()));

        var decider = Deciders.startMatch(match);
        var command = new StartMatchCommand(match.matchID());

        var events = decider.decide().apply(command, match);
        assertEquals(1, events.size());
        assertTrue(events.get(0) instanceof MatchEvent.MatchStarted);
        var newState = Deciders.applyAllEventsToState(match, events, decider.evolve());
        assertTrue(newState instanceof Match.MatchInProgress);
    }
}
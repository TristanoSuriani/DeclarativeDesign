package nl.suriani.declarative_design.examples.football.usecase;

import nl.suriani.declarative_design.examples.football.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PlanNewMatchUseCaseTest {
    private final Match.NewMatch NEW_MATCH = new Match.NewMatch(
            new MatchID(UUID.randomUUID()),
            LocalDateTime.now().plusDays(13),
            new MatchName("Milan vs Ajax"),
            new HomeTeamID(UUID.randomUUID()),
            new AwayTeamID(UUID.randomUUID()));
    private final MatchRepository matchRepository_findsMatch = new MatchRepository() {
        @Override
        public Optional<Match> findById(MatchID matchID) {
            return Optional.of(NEW_MATCH);
        }

        @Override
        public void save(Match match) {

        }
    };

    private final MatchRepository matchRepository_findsNothing = new MatchRepository() {
        @Override
        public Optional<Match> findById(MatchID matchID) {
            return Optional.empty();
        }

        @Override
        public void save(Match match) {

        }
    };

    @Test
    void matchWasAlreadyPlanned() {
        var match = NEW_MATCH;

        var command = new PlanNewMatchCommand(match.matchID(),
                match.dateTime(),
                match.name(),
                match.homeTeamID(),
                match.awayTeamID());

        var useCase = new PlanNewMatchUseCase(matchRepository_findsMatch);
        var response = useCase.plan(command);
        assertTrue(response.newState() instanceof Match.NewMatch);
        assertTrue(response.events().isEmpty());
    }

    @Test
    void matchIsPlannedAsExpected() {
        var match = NEW_MATCH;

        var command = new PlanNewMatchCommand(match.matchID(),
                match.dateTime(),
                match.name(),
                match.homeTeamID(),
                match.awayTeamID());

        var useCase = new PlanNewMatchUseCase(matchRepository_findsNothing);
        var response = useCase.plan(command);
        assertTrue(response.newState() instanceof Match.NewMatch);
        assertEquals(match, response.newState());
        assertEquals(1, response.events().size());
        assertTrue(response.events().get(0) instanceof MatchEvent.NewMatchCreated);
    }
}
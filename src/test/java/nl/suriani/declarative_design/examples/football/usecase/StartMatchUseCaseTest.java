package nl.suriani.declarative_design.examples.football.usecase;

import nl.suriani.declarative_design.examples.football.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class StartMatchUseCaseTest {

    private static final MatchID matchID = new MatchID();

    private static final Match.MatchInProgress matchInProgress = new Match.MatchInProgress(
            new MatchID(UUID.randomUUID()),
            LocalDateTime.now().plusDays(13),
            new MatchName("Milan vs Ajax"),
            new HomeTeamID(UUID.randomUUID()),
            new AwayTeamID(UUID.randomUUID()),
            new Score());

    private static final Match.NewMatch newMatch = new Match.NewMatch(
            new MatchID(UUID.randomUUID()),
            LocalDateTime.now().plusDays(13),
            new MatchName("Milan vs Ajax"),
            new HomeTeamID(UUID.randomUUID()),
            new AwayTeamID(UUID.randomUUID()));

    private static final Match.MatchTerminated matchTerminated = new Match.MatchTerminated(
            new MatchID(UUID.randomUUID()),
            LocalDateTime.now().plusDays(13),
            new MatchName("Milan vs Ajax"),
            new HomeTeamID(UUID.randomUUID()),
            new AwayTeamID(UUID.randomUUID()),
            new Score(10, 0));

    private final MatchRepository matchRepository_findsAMatchInProgress = new MatchRepository() {
        @Override
        public Optional<Match> findById(MatchID matchID) {
            return Optional.of(matchInProgress);
        }

        @Override
        public void save(Match match) {

        }
    };

    private final MatchRepository matchRepository_findsANewMatch = new MatchRepository() {
        @Override
        public Optional<Match> findById(MatchID matchID) {
            return Optional.of(newMatch);
        }

        @Override
        public void save(Match match) {

        }
    };

    private final MatchRepository matchRepository_findsATerminatedMatch = new MatchRepository() {
        @Override
        public Optional<Match> findById(MatchID matchID) {
            return Optional.of(matchTerminated);
        }

        @Override
        public void save(Match match) {

        }
    };

    @Test
    void startMatch_wasAlreadyInProgress_noEvents() {
        var command = new StartMatchCommand(matchID);
        var useCase = new StartMatchUseCase(matchRepository_findsAMatchInProgress);
        var response = useCase.start(command);

        assertTrue(response.events().isEmpty());
        assertEquals(matchInProgress, response.newState());
    }

    @Test
    void startMatch_wasStillNew_matchStarted() {
        var command = new StartMatchCommand(matchID);
        var useCase = new StartMatchUseCase(matchRepository_findsANewMatch);
        var response = useCase.start(command);

        assertEquals(1, response.events().size());
        var event = response.events().get(0);
        assertTrue(event instanceof MatchEvent.MatchStarted);
        assertTrue(response.newState() instanceof Match.MatchInProgress);
    }

    @Test
    void startMatch_wasInSomeNotSupportedState_fail() {
        var command = new StartMatchCommand(matchID);
        var useCase = new StartMatchUseCase(matchRepository_findsATerminatedMatch);
        var exception = assertThrows(UseCaseFailureException.class, () -> useCase.start(command));
        assertEquals("Match is in invalid state: MatchTerminated", exception.getMessage());
    }
}
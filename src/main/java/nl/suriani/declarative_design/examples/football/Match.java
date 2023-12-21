package nl.suriani.declarative_design.examples.football;

import java.util.List;

public sealed interface Match {
    record GenericMatch(MatchID matchID,
                               MatchStatus status,
                               MatchName name,
                               TeamID team1ID,
                               TeamID team2ID,
                               List<PlayerEvent> playerEvents) {

        public GenericMatch {
            Guard.areNotNull(matchID, name, team1ID, team2ID, playerEvents);
            playerEvents = List.copyOf(playerEvents);
        }

        public GenericMatch(TeamID team1ID, TeamID team2ID) {
            this(new MatchID(), MatchStatus.NEW, new MatchName(), team1ID, team2ID, List.of());
        }

        public boolean isInProgress() {
            return status == MatchStatus.IN_PROGRESS;
        }
    }

}


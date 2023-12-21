package nl.suriani.declarative_design.examples.football;

public sealed interface Match {

    record NewMatch() implements Match {}

    record MatchCancelled() implements Match {}

    record MatchSuspended(MatchID matchID,
                          MatchName name,
                          HomeTeamID homeTeamID,
                          AwayTeamID awayTeamID,
                          Score score) implements Match {

        public MatchSuspended {
            Guard.areNotNull(matchID, name, homeTeamID, awayTeamID, score);
        }
    }

    record MatchInProgress(MatchID matchID,
                           MatchName name,
                           HomeTeamID homeTeamID,
                           AwayTeamID awayTeamID,
                           Score score) implements Match {

        public MatchInProgress {
            Guard.areNotNull(matchID, name, homeTeamID, awayTeamID, score);
        }
    }

    record MatchTerminated(MatchID matchID,
                           MatchName name,
                           HomeTeamID homeTeamID,
                           AwayTeamID awayTeamID,
                           Score score) implements Match {

        public MatchTerminated {
            Guard.areNotNull(matchID, name, homeTeamID, awayTeamID, score);
        }
    }

}


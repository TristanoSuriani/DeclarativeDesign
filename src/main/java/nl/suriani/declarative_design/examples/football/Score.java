package nl.suriani.declarative_design.examples.football;

public record Score(int scoreHomeTeam, int scoreAwayTeam) {
    public Score {
        Guard.value(scoreHomeTeam)
                .isPositive();

        Guard.value(scoreAwayTeam)
                .isPositive();
    }

    public Score() {
        this(0, 0);
    }

    public Score homeTeamScores() {
        return new Score(scoreHomeTeam + 1, scoreAwayTeam);
    }

    public Score awayTeamScores() {
        return new Score(scoreHomeTeam, scoreAwayTeam + 1);
    }
}

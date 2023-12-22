package nl.suriani.declarative_design.examples.football.usecase;

import nl.suriani.declarative_design.examples.football.Match;
import nl.suriani.declarative_design.examples.football.MatchID;

import java.util.Optional;

public interface MatchRepository {
    Optional<Match> findById(MatchID matchID);
    void save(Match match);
}

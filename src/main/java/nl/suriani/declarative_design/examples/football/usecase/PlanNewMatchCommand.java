package nl.suriani.declarative_design.examples.football.usecase;

import nl.suriani.declarative_design.examples.football.AwayTeamID;
import nl.suriani.declarative_design.examples.football.HomeTeamID;
import nl.suriani.declarative_design.examples.football.MatchID;
import nl.suriani.declarative_design.examples.football.MatchName;

import java.time.LocalDateTime;

public record PlanNewMatchCommand(MatchID matchID,
                                  LocalDateTime dateTime,
                                  MatchName name,
                                  HomeTeamID homeTeamID,
                                  AwayTeamID awayTeamID) {
}

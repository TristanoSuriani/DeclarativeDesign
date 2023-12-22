package nl.suriani.declarative_design.examples.football.usecase;

import nl.suriani.declarative_design.examples.football.MatchID;

public record StartMatchCommand(MatchID matchID) {
}

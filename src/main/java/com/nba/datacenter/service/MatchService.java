package com.nba.datacenter.service;


import org.springframework.http.ResponseEntity;

public interface MatchService {
    ResponseEntity<?> getLastMonthMatches();

    ResponseEntity<?> getMatchesByDates(String startDate, String endDate);

    ResponseEntity<?> getTeamMatchStatByMatchId(String matchId);

    ResponseEntity<?> getPlayerMatchDataByMatchID(boolean isHome, String matchId);

    ResponseEntity<?> getTeamTotalStatByMatchId(boolean isHome, String matchId);
}

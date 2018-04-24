package com.nba.datacenter.service;


import org.springframework.http.ResponseEntity;

public interface MatchService {
    ResponseEntity<?> getLastMonthMatches();

    ResponseEntity<?> getMatchesByDates(String startDate, String endDate);
}

package com.nba.datacenter.controller;

import com.nba.datacenter.service.MatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/matches")
    public ResponseEntity<?> getLastMonthMatches() {
        return matchService.getLastMonthMatches();
    }

    @GetMapping("/match_dates")
    public ResponseEntity<?> getMatchesByDates(@RequestParam String startDate,
                                               @RequestParam String endDate) {
        return matchService.getMatchesByDates(startDate, endDate);
    }
}

package com.nba.datacenter.controller;

import com.nba.datacenter.service.StatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatController {
    private final StatService statService;

    public StatController(StatService statService) {
        this.statService = statService;
    }

    @GetMapping("/rank/{rankType}")
    public ResponseEntity<?> getStatRankByRankType(@PathVariable String rankType) {
        return statService.getStatRankByRankType(rankType);
    }

    @GetMapping("/personal_data/{playerId}")
    public ResponseEntity<?> getPersonalDataByPlayerId(@PathVariable String playerId) {
        return statService.getPersonalDataByPlayerId(playerId);
    }

    @GetMapping("/tables")
    public ResponseEntity<?> getTableRecordsNum() {
        return statService.getTableRecordsNum();
    }
}

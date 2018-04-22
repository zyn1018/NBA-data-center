package com.nba.datacenter.controller;

import com.nba.datacenter.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players/{initial}")
    public ResponseEntity<?> getPlayersByInitial(@PathVariable String initial) {
        return playerService.getPlayersByInitial(initial.toUpperCase());
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<?> getPlayerById(@PathVariable String playerId) {
        return playerService.getPlayerInfoById(playerId);
    }


    @GetMapping("/roster/{teamId}")
    public ResponseEntity<?> getTeamRosterByTeamId(@PathVariable String teamId) {
        return playerService.getTeamRosterByTeamId(teamId);
    }

    @GetMapping("/rank/{rankType}")
    public ResponseEntity<?> getTop5PlayersByRankType(@PathVariable String rankType) {
        return playerService.getTop5PlayerByRankType(rankType);
    }
}

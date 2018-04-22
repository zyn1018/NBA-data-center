package com.nba.datacenter.controller;

import com.nba.datacenter.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<?> getTeamById(@PathVariable String teamId) {
        return teamService.getTeamById(teamId);
    }

    @GetMapping("/teams/{division}")
    public ResponseEntity<?> getTeamsByDivision(@PathVariable String division) {
        return teamService.getTeamByDivision(division.toUpperCase());
    }

    @GetMapping("/team_via_playerid/{playerId}")
    public ResponseEntity<?> getTeamByPlayerId(@PathVariable String playerId) {
        return teamService.getTeamByPlayerId(playerId);
    }
}

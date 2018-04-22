package com.nba.datacenter.service;

import org.springframework.http.ResponseEntity;

public interface TeamService {
    ResponseEntity<?> getTeamById(String teamId);

    ResponseEntity<?> getTeamByDivision(String division);

    ResponseEntity<?> getTeamByPlayerId(String playerId);
}

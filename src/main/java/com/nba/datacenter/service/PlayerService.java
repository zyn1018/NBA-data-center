package com.nba.datacenter.service;

import org.springframework.http.ResponseEntity;

public interface PlayerService {
    ResponseEntity<?> getPlayersByInitial(String s);

    ResponseEntity<?> getPlayerInfoById(String id);

    ResponseEntity<?> getTeamRosterByTeamId(String teamId);

    ResponseEntity<?> getTop5PlayerByRankType(String rankType);
}

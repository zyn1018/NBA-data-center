package com.nba.datacenter.service;

import org.springframework.http.ResponseEntity;

public interface StatService {
    ResponseEntity<?> getStatRankByRankType(String rankType);

    ResponseEntity<?> getPersonalDataByPlayerId(String playerId);

    ResponseEntity<?> getTableRecordsNum();
}

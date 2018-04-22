package com.nba.datacenter.service.impl;

import com.nba.datacenter.dao.StatDao;
import com.nba.datacenter.domain.PersonalData;
import com.nba.datacenter.domain.Stat;
import com.nba.datacenter.service.StatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("StatService")
public class StatServiceImpl implements StatService {
    private final StatDao statDao;

    public StatServiceImpl(StatDao statDao) {
        this.statDao = statDao;
    }

    @Override
    public ResponseEntity<?> getStatRankByRankType(String rankType) {
        if (rankType.equals("points") || rankType.equals("rebounds") || rankType.equals("assists") || rankType.equals("blocks")) {
            List<Stat> statList = statDao.getStatRankByRankType(rankType);
            if (statList != null) {
                return new ResponseEntity<>(statList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getPersonalDataByPlayerId(String playerId) {
        if (!playerId.equals("") && playerId.length() > 0) {
            PersonalData personalData = statDao.getPersonalDataByPlayerId(playerId);
            if (personalData != null) {
                return new ResponseEntity<>(personalData, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

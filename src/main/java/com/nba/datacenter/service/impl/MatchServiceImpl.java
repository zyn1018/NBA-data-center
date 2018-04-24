package com.nba.datacenter.service.impl;

import com.nba.datacenter.dao.MatchDao;
import com.nba.datacenter.domain.Match;
import com.nba.datacenter.service.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MatchService")
public class MatchServiceImpl implements MatchService {

    private final MatchDao matchDao;

    public MatchServiceImpl(MatchDao matchDao) {
        this.matchDao = matchDao;
    }


    @Override
    public ResponseEntity<?> getLastMonthMatches() {
        List<Match> matchList = matchDao.getLastMonthMatches();
        if (matchList != null) {
            return new ResponseEntity<>(matchList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<?> getMatchesByDates(String startDate, String endDate) {
        if (startDate == null || endDate == null || startDate.length() == 0 || endDate.length() == 0 || startDate.length() > 10 || endDate.length() > 10) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            List<Match> matchList = matchDao.getMatchesByDates(startDate, endDate);
            if (matchList != null) {
                return new ResponseEntity<>(matchList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
    }
}

package com.nba.datacenter.service.impl;

import com.nba.datacenter.dao.TeamDao;
import com.nba.datacenter.domain.Team;
import com.nba.datacenter.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TeamService")
public class TeamServiceImpl implements TeamService {

    private final TeamDao teamDao;

    public TeamServiceImpl(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    @Override
    public ResponseEntity<?> getTeamById(String teamId) {
        if (!teamId.equals("") && teamId.length() > 0) {
            Team team = teamDao.getTeamByTeamId(teamId);
            if (team != null) {
                return new ResponseEntity<>(team, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> getTeamByDivision(String division) {
        if (!division.equals("E") && !division.equals("W")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            List<Team> teamList = teamDao.getTeamByDivision(division);
            if (teamList != null) {
                return new ResponseEntity<>(teamList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
    }

    @Override
    public ResponseEntity<?> getTeamByPlayerId(String playerId) {
        if (playerId == null || playerId.equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Team team = teamDao.getTeamByPlayerId(playerId);
            if (team != null) {
                return new ResponseEntity<>(team, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
    }
}

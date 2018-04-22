package com.nba.datacenter.service.impl;

import com.nba.datacenter.dao.PlayerDao;
import com.nba.datacenter.domain.Player;
import com.nba.datacenter.service.PlayerService;
import com.nba.datacenter.vo.RankVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PlayerService")
public class PlayerServiceImpl implements PlayerService {

    private final PlayerDao playerDao;

    public PlayerServiceImpl(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }


    @Override
    public ResponseEntity<?> getPlayersByInitial(String s) {
        List<Player> playerList = playerDao.findPlayersByInitial(s);
        if (playerList != null) {
            return new ResponseEntity<>(playerList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<?> getPlayerInfoById(String id) {
        Player player = playerDao.findPlayersById(id);
        if (player != null) {
            return new ResponseEntity<>(player, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<?> getTeamRosterByTeamId(String teamId) {
        if (!teamId.equals("") && teamId.length() > 0) {
            List<Player> playerList = playerDao.getTeamRosterByTeamId(teamId);
            if (playerList != null) {
                return new ResponseEntity<>(playerList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getTop5PlayerByRankType(String rankType) {
        if (!rankType.equals("pts") && !rankType.equals("reb")
                && !rankType.equals("ast") && !rankType.equals("blk")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            List<RankVo> rankVoList = playerDao.getTop5PlayersRank(rankType);
            if (rankVoList != null) {
                return new ResponseEntity<>(rankVoList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
    }
}

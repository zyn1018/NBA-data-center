package com.nba.datacenter.dao;

import com.nba.datacenter.domain.Player;
import com.nba.datacenter.utils.OracleUtil;
import com.nba.datacenter.vo.RankVo;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerDao {
    public List<Player> findPlayersByInitial(String s) {
        try {
            Connection conn = OracleUtil.getConnection();
            List<Player> playerList = new ArrayList<>();
            String sql = "SELECT * FROM PLAYER WHERE NAME LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, s + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Player player = rsToPlayer(rs);
                playerList.add(player);
            }
            OracleUtil.stopConnection(conn, pstmt);
            return playerList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Player findPlayersById(String id) {
        try {
            Connection conn = OracleUtil.getConnection();
            String sql = "SELECT P.ID, P.NAME, T.CITY || ' ' || T.NICKNAME AS TEAM, P.AGE, P.HEIGHT, P.WEIGHT, " +
                    "P.COLLEGE, P.COUNTRY, P.DRAFTYEAR, P.DRAFTROUND, P.DRAFTNUMBER " +
                    "FROM PLAYER P,TEAM_COMMON T WHERE " +
                    "P.TEAM = T.ABBREVIATION AND P.ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            Player player = new Player();
            pstmt.setInt(1, Integer.parseInt(id));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                player = rsToPlayer(rs);
            }
            OracleUtil.stopConnection(conn, pstmt);
            return player;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Player> getTeamRosterByTeamId(String teamId) {
        try {
            Connection conn = OracleUtil.getConnection();
            String sql = "SELECT P.* FROM PLAYER P, TEAM_COMMON T " +
                    "WHERE P.TEAM = T.ABBREVIATION " +
                    "AND T.TEAM_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            List<Player> playerList = new ArrayList<>();
            pstmt.setString(1, teamId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Player player = rsToPlayer(rs);
                playerList.add(player);
            }
            OracleUtil.stopConnection(conn, pstmt);
            return playerList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<RankVo> getTop5PlayersRank(String rankType) {
        try {
            Connection conn = OracleUtil.getConnection();
            String sql = "";
            switch (rankType) {
                case "pts":
                    sql = "SELECT * " +
                            "FROM (SELECT " +
                            "        p.ID, " +
                            "        P.NAME,  " +
                            "        ROUND(AVG(PS.PTS), 1) AS DATA " +
                            "      FROM PLAYERSTAT PS, PLAYER P " +
                            "      WHERE PS.PLAYER_ID = P.ID " +
                            "      GROUP BY P.ID, P.NAME " +
                            "      ORDER BY AVG(PS.PTS) DESC) " +
                            "WHERE ROWNUM <= 5";
                    break;
                case "reb":
                    sql = "SELECT *\n" +
                            "FROM (SELECT\n" +
                            "        p.ID,\n" +
                            "        P.NAME,\n" +
                            "        ROUND(AVG(PS.REB), 1) AS DATA\n" +
                            "      FROM PLAYERSTAT PS, PLAYER P\n" +
                            "      WHERE PS.PLAYER_ID = P.ID\n" +
                            "      GROUP BY P.ID, P.NAME\n" +
                            "      ORDER BY AVG(PS.REB) DESC)\n" +
                            "WHERE ROWNUM <= 5";
                    break;
                case "ast":
                    sql = "SELECT *\n" +
                            "FROM (SELECT\n" +
                            "        p.ID,\n" +
                            "        P.NAME,\n" +
                            "        ROUND(AVG(PS.AST), 1) AS DATA\n" +
                            "      FROM PLAYERSTAT PS, PLAYER P\n" +
                            "      WHERE PS.PLAYER_ID = P.ID\n" +
                            "      GROUP BY P.ID, P.NAME\n" +
                            "      ORDER BY AVG(PS.AST) DESC)\n" +
                            "WHERE ROWNUM <= 5";
                    break;
                case "blk":
                    sql = "SELECT *\n" +
                            "FROM (SELECT\n" +
                            "        p.ID,\n" +
                            "        P.NAME,\n" +
                            "        ROUND(AVG(PS.BLK), 1) AS DATA\n" +
                            "      FROM PLAYERSTAT PS, PLAYER P\n" +
                            "      WHERE PS.PLAYER_ID = P.ID\n" +
                            "      GROUP BY P.ID, P.NAME\n" +
                            "      ORDER BY AVG(PS.BLK) DESC)\n" +
                            "WHERE ROWNUM <= 5";
                    break;
            }
            System.out.println(sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            List<RankVo> rankVoList = new ArrayList<>();
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                RankVo rankVo = rsToRankVo(rs);
                rankVoList.add(rankVo);
            }
            OracleUtil.stopConnection(conn, pstmt);
            return rankVoList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Player rsToPlayer(ResultSet rs) throws SQLException {
        int playerId = rs.getInt("ID");
        String name = rs.getString("NAME");
        String team = rs.getString("TEAM");
        int age = rs.getInt("AGE");
        int height = rs.getInt("HEIGHT");
        int weight = rs.getInt("WEIGHT");
        String college = rs.getString("COLLEGE");
        String country = rs.getString("COUNTRY");
        String draftYear = rs.getString("DRAFTYEAR");
        String draftRound = rs.getString("DRAFTROUND");
        String draftNumber = rs.getString("DRAFTNUMBER");
        return new Player(playerId, name, team, age, height, weight, college, country, draftYear, draftRound, draftNumber);
    }

    private RankVo rsToRankVo(ResultSet rs) throws SQLException {
        int playerId = rs.getInt("ID");
        String name = rs.getString("NAME");
        double data = rs.getDouble("DATA");
        return new RankVo(playerId, name, data);
    }
}

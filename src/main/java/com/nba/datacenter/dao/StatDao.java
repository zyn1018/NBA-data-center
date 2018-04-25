package com.nba.datacenter.dao;

import com.nba.datacenter.domain.PersonalData;
import com.nba.datacenter.domain.Stat;
import com.nba.datacenter.domain.TableRecords;
import com.nba.datacenter.utils.OracleUtil;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class StatDao {

    /**
     * Get all player's performance data in terms of a certain ranking type
     *
     * @param rankType
     * @return
     */
    public List<Stat> getStatRankByRankType(String rankType) {
        try {
            Connection conn = OracleUtil.getConnection();
            List<Stat> statList = new ArrayList<>();
            String sql = "";
            switch (rankType) {
                case "points":
                    sql = "SELECT S.*, T.ICON FROM (SELECT\n" +
                            "  PLAYER_ID,\n" +
                            "  NAME,\n" +
                            "  ROUND(AVG(PTS), 1) AS POINTS,\n" +
                            "  ROUND(AVG(REB), 1) AS REBS,\n" +
                            "  ROUND(AVG(AST), 1) AS ASTS,\n" +
                            "  ROUND(AVG(FG_PCT), 2) AS FGPCT,\n" +
                            "  ROUND(AVG(FT_PCT), 2) AS FTPCT,\n" +
                            "  ROUND(AVG(FGTHREE_PCT), 2) AS THREEPCT,\n" +
                            "  ROUND(AVG(STL), 1) AS STLS,\n" +
                            "  ROUND(AVG(BLK), 1) AS BLKS,\n" +
                            "  ROUND(AVG(PLUS_MINS), 1) AS EFF\n" +
                            "FROM PLAYERSTAT, PLAYER\n" +
                            "WHERE PLAYER_ID = ID\n" +
                            "GROUP BY PLAYER_ID, NAME\n" +
                            "ORDER BY POINTS DESC) S, TEAM_COMMON T, PLAYER PL WHERE S.PLAYER_ID = PL.ID\n" +
                            "AND T.ABBREVIATION = PL.TEAM";
                    break;
                case "rebounds":
                    sql = "SELECT S.*, T.ICON FROM (SELECT\n" +
                            "  PLAYER_ID,\n" +
                            "  NAME,\n" +
                            "  ROUND(AVG(PTS), 1) AS POINTS,\n" +
                            "  ROUND(AVG(REB), 1) AS REBS,\n" +
                            "  ROUND(AVG(AST), 1) AS ASTS,\n" +
                            "  ROUND(AVG(FG_PCT), 2) AS FGPCT,\n" +
                            "  ROUND(AVG(FT_PCT), 2) AS FTPCT,\n" +
                            "  ROUND(AVG(FGTHREE_PCT), 2) AS THREEPCT,\n" +
                            "  ROUND(AVG(STL), 1) AS STLS,\n" +
                            "  ROUND(AVG(BLK), 1) AS BLKS,\n" +
                            "  ROUND(AVG(PLUS_MINS), 1) AS EFF\n" +
                            "FROM PLAYERSTAT, PLAYER\n" +
                            "WHERE PLAYER_ID = ID\n" +
                            "GROUP BY PLAYER_ID, NAME\n" +
                            "ORDER BY REBS DESC) S, TEAM_COMMON T, PLAYER PL WHERE S.PLAYER_ID = PL.ID\n" +
                            "AND T.ABBREVIATION = PL.TEAM";
                    break;
                case "assists":
                    sql = "SELECT S.*, T.ICON FROM (SELECT\n" +
                            "  PLAYER_ID,\n" +
                            "  NAME,\n" +
                            "  ROUND(AVG(PTS), 1) AS POINTS,\n" +
                            "  ROUND(AVG(REB), 1) AS REBS,\n" +
                            "  ROUND(AVG(AST), 1) AS ASTS,\n" +
                            "  ROUND(AVG(FG_PCT), 2) AS FGPCT,\n" +
                            "  ROUND(AVG(FT_PCT), 2) AS FTPCT,\n" +
                            "  ROUND(AVG(FGTHREE_PCT), 2) AS THREEPCT,\n" +
                            "  ROUND(AVG(STL), 1) AS STLS,\n" +
                            "  ROUND(AVG(BLK), 1) AS BLKS,\n" +
                            "  ROUND(AVG(PLUS_MINS), 1) AS EFF\n" +
                            "FROM PLAYERSTAT, PLAYER\n" +
                            "WHERE PLAYER_ID = ID\n" +
                            "GROUP BY PLAYER_ID, NAME\n" +
                            "ORDER BY ASTS DESC) S, TEAM_COMMON T, PLAYER PL WHERE S.PLAYER_ID = PL.ID\n" +
                            "AND T.ABBREVIATION = PL.TEAM";
                    break;
                case "blocks":
                    sql = "SELECT S.*, T.ICON FROM (SELECT\n" +
                            "  PLAYER_ID,\n" +
                            "  NAME,\n" +
                            "  ROUND(AVG(PTS), 1) AS POINTS,\n" +
                            "  ROUND(AVG(REB), 1) AS REBS,\n" +
                            "  ROUND(AVG(AST), 1) AS ASTS,\n" +
                            "  ROUND(AVG(FG_PCT), 2) AS FGPCT,\n" +
                            "  ROUND(AVG(FT_PCT), 2) AS FTPCT,\n" +
                            "  ROUND(AVG(FGTHREE_PCT), 2) AS THREEPCT,\n" +
                            "  ROUND(AVG(STL), 1) AS STLS,\n" +
                            "  ROUND(AVG(BLK), 1) AS BLKS,\n" +
                            "  ROUND(AVG(PLUS_MINS), 1) AS EFF\n" +
                            "FROM PLAYERSTAT, PLAYER\n" +
                            "WHERE PLAYER_ID = ID\n" +
                            "GROUP BY PLAYER_ID, NAME\n" +
                            "ORDER BY BLKS DESC) S, TEAM_COMMON T, PLAYER PL WHERE S.PLAYER_ID = PL.ID\n" +
                            "AND T.ABBREVIATION = PL.TEAM";
                    break;
            }
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Stat stat = rsToStat(rs);
                statList.add(stat);
            }
            OracleUtil.stopConnection(conn, pstmt);
            return statList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get a player's average data by his player id
     *
     * @param playerId
     * @return
     */
    public PersonalData getPersonalDataByPlayerId(String playerId) {
        try {
            Connection conn = OracleUtil.getConnection();
            PersonalData personalData = new PersonalData();
            String sql = "SELECT\n" +
                    "  ROUND(AVG(PTS), 1) AS POINTS,\n" +
                    "  ROUND(AVG(REB), 1) AS REBOUNDS,\n" +
                    "  ROUND(AVG(AST), 1) AS ASSISTS,\n" +
                    "  ROUND(AVG(BLK), 1) AS BLOCKS,\n" +
                    "  ROUND(AVG(STL), 1) AS STEALS,\n" +
                    "  ROUND(AVG(TOF), 1) AS TURNOVERS\n" +
                    "FROM PLAYERSTAT\n" +
                    "WHERE PLAYER_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(playerId));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                personalData = rsToPersonalData(rs);
            }
            OracleUtil.stopConnection(conn, pstmt);
            return personalData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the number of tuples in the database
     *
     * @return
     */
    public List<TableRecords> getTableRecords() {
        try {
            Connection conn = OracleUtil.getConnection();
            List<TableRecords> tableRecordsList = new ArrayList<>();
            String sql = "SELECT\n" +
                    "  T.TABLE_NAME,\n" +
                    "  T.NUM_ROWS\n" +
                    "FROM USER_TABLES T";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                TableRecords tableRecords = new TableRecords();
                tableRecords.setTableName(rs.getString("TABLE_NAME"));
                tableRecords.setNumRows(rs.getInt("NUM_ROWS"));
                tableRecordsList.add(tableRecords);
            }
            OracleUtil.stopConnection(conn, pstmt);
            return tableRecordsList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Stat rsToStat(ResultSet rs) throws SQLException {
        int playerId = rs.getInt("PLAYER_ID");
        String name = rs.getString("NAME");
        double points = rs.getDouble("POINTS");
        double reb = rs.getDouble("REBS");
        double ast = rs.getDouble("ASTS");
        double fgpct = rs.getDouble("FGPCT");
        double ftpct = rs.getDouble("FTPCT");
        double threepct = rs.getDouble("THREEPCT");
        double stl = rs.getDouble("STLS");
        double blk = rs.getDouble("BLKS");
        double eff = rs.getDouble("EFF");
        String icon = rs.getString("ICON");
        return new Stat(playerId, name, points, reb, ast, fgpct, ftpct, threepct, stl, blk, eff, icon);
    }

    private PersonalData rsToPersonalData(ResultSet rs) throws SQLException {
        double points = rs.getDouble("POINTS");
        double rebounds = rs.getDouble("REBOUNDS");
        double assists = rs.getDouble("ASSISTS");
        double blocks = rs.getDouble("BLOCKS");
        double steals = rs.getDouble("STEALS");
        double turnovers = rs.getDouble("TURNOVERS");
        return new PersonalData(points, rebounds, assists, blocks, steals, turnovers);
    }
}

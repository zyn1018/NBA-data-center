package com.nba.datacenter.dao;

import com.nba.datacenter.domain.Match;
import com.nba.datacenter.utils.OracleUtil;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class MatchDao {
    public List<Match> getLastMonthMatches() {
        try {
            Connection conn = OracleUtil.getConnection();
            List<Match> matchList = new ArrayList<>();
            String sql = "SELECT\n" +
                    "  N1.MATCH_ID,\n" +
                    "  N1.MATCH_DATE,\n" +
                    "  N1.TEAM_ID AS HOME_ID,\n" +
                    "  N1.NAME    AS HOME,\n" +
                    "  N2.TEAM_ID AS AWAY_ID,\n" +
                    "  N2.NAME    AS AWAY\n" +
                    "FROM (\n" +
                    "       SELECT\n" +
                    "         M.MATCH_ID,\n" +
                    "         MATCH_DATE,\n" +
                    "         T.TEAM_ID,\n" +
                    "         T.CITY || ' ' || T.NICKNAME AS NAME\n" +
                    "       FROM TEAM_COMMON T, MATCH M\n" +
                    "       WHERE M.HOME_ID = T.TEAM_ID\n" +
                    "     ) N1, (SELECT\n" +
                    "              M2.MATCH_ID,\n" +
                    "              T2.CITY || ' ' || T2.NICKNAME AS NAME,\n" +
                    "              T2.TEAM_ID\n" +
                    "            FROM TEAM_COMMON T2, MATCH M2\n" +
                    "            WHERE M2.VISITOR_ID = T2.TEAM_ID) N2\n" +
                    "WHERE N1.MATCH_ID = N2.MATCH_ID AND N1.MATCH_DATE >= ADD_MONTHS(SYSDATE, -1) AND N1.MATCH_DATE <= CURRENT_DATE\n" +
                    "ORDER BY N1.MATCH_DATE DESC";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Match match = rsToMatch(rs);
                matchList.add(match);
            }
            OracleUtil.stopConnection(conn, pstmt);
            return matchList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Match> getMatchesByDates(String startDate, String endDate) {
        try {
            Connection conn = OracleUtil.getConnection();
            List<Match> matchList = new ArrayList<>();
            String sql = "SELECT\n" +
                    "  N1.MATCH_ID,\n" +
                    "  N1.MATCH_DATE,\n" +
                    "  N1.TEAM_ID AS HOME_ID,\n" +
                    "  N1.NAME    AS HOME,\n" +
                    "  N2.TEAM_ID AS AWAY_ID,\n" +
                    "  N2.NAME    AS AWAY\n" +
                    "FROM (\n" +
                    "       SELECT\n" +
                    "         M.MATCH_ID,\n" +
                    "         MATCH_DATE,\n" +
                    "         T.TEAM_ID,\n" +
                    "         T.CITY || ' ' || T.NICKNAME AS NAME\n" +
                    "       FROM TEAM_COMMON T, MATCH M\n" +
                    "       WHERE M.HOME_ID = T.TEAM_ID\n" +
                    "     ) N1, (SELECT\n" +
                    "              M2.MATCH_ID,\n" +
                    "              T2.CITY || ' ' || T2.NICKNAME AS NAME,\n" +
                    "              T2.TEAM_ID\n" +
                    "            FROM TEAM_COMMON T2, MATCH M2\n" +
                    "            WHERE M2.VISITOR_ID = T2.TEAM_ID) N2\n" +
                    "WHERE N1.MATCH_ID = N2.MATCH_ID AND N1.MATCH_DATE >= ? AND N1.MATCH_DATE <= ? \n" +
                    "ORDER BY N1.MATCH_DATE DESC";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            Date startDateSql = new Date(new SimpleDateFormat("MM/dd/yyyy").parse(startDate).getTime());
            Date endDateSql = new Date(new SimpleDateFormat("MM/dd/yyyy").parse(endDate).getTime());
            pstmt.setDate(1, startDateSql);
            pstmt.setDate(2, endDateSql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Match match = rsToMatch(rs);
                matchList.add(match);
            }
            OracleUtil.stopConnection(conn, pstmt);
            return matchList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private Match rsToMatch(ResultSet rs) throws SQLException {
        int matchId = rs.getInt("MATCH_ID");
        Date date = rs.getDate("MATCH_DATE");
        int homeId = rs.getInt("HOME_ID");
        String homeName = rs.getString("HOME");
        int awayId = rs.getInt("AWAY_ID");
        String awayName = rs.getString("AWAY");
        return new Match(matchId, date, homeId, homeName, awayId, awayName);
    }
}

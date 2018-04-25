package com.nba.datacenter.dao;

import com.nba.datacenter.domain.Match;
import com.nba.datacenter.domain.PersonalMatchData;
import com.nba.datacenter.domain.TeamMatchStat;
import com.nba.datacenter.domain.TeamTotalStat;
import com.nba.datacenter.utils.OracleUtil;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class MatchDao {

    /**
     * Get all games played in the last month
     *
     * @return
     */
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

    /**
     * Get all games in a certain period of time
     *
     * @param startDate
     * @param endDate
     * @return
     */
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
                    "ORDER BY N1.MATCH_DATE ASC";
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

    /**
     * Get teams' ids, icons, names played against in a match by the match id
     *
     * @param matchId
     * @return
     */
    public TeamMatchStat getTeamMatchStatByMatchId(String matchId) {
        try {
            Connection conn = OracleUtil.getConnection();
            String sql = "SELECT *\n" +
                    "FROM (SELECT\n" +
                    "        T.TEAM_ID                   AS HOME_ID,\n" +
                    "        T.ICON                      AS HOME_ICON,\n" +
                    "        T.CITY || ' ' || T.NICKNAME AS HOME_NAME,\n" +
                    "        SUM(P.PTS)                  AS HOME_SCORES\n" +
                    "      FROM MATCH M, TEAM_COMMON T, PLAYERSTAT P\n" +
                    "      WHERE M.MATCH_ID = P.MATCH_ID\n" +
                    "            AND P.TEAM_ID = M.HOME_ID\n" +
                    "            AND T.TEAM_ID = M.HOME_ID AND M.MATCH_ID = ?\n" +
                    "      GROUP BY T.TEAM_ID, T.CITY, T.NICKNAME, T.ICON),\n" +
                    "  (SELECT\n" +
                    "     T.TEAM_ID                   AS AWAY_ID,\n" +
                    "     T.ICON                      AS AWAY_ICON,\n" +
                    "     T.CITY || ' ' || T.NICKNAME AS AWAY_NAME,\n" +
                    "     SUM(P.PTS)                  AS AWAY_SCORES\n" +
                    "   FROM MATCH M, TEAM_COMMON T, PLAYERSTAT P\n" +
                    "   WHERE M.MATCH_ID = P.MATCH_ID\n" +
                    "         AND P.TEAM_ID = M.VISITOR_ID\n" +
                    "         AND T.TEAM_ID = M.VISITOR_ID AND M.MATCH_ID = ?\n" +
                    "   GROUP BY T.TEAM_ID, T.CITY, T.NICKNAME, T.ICON)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            TeamMatchStat teamMatchStat = new TeamMatchStat();
            pstmt.setInt(1, Integer.parseInt(matchId));
            pstmt.setInt(2, Integer.parseInt(matchId));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                teamMatchStat = rsToTeamMatchStat(rs);
            }
            OracleUtil.stopConnection(conn, pstmt);
            return teamMatchStat;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get all performance data of players who belongs to home or visitor team
     *
     * @param isHome
     * @param matchId
     * @return
     */
    public List<PersonalMatchData> getPlayersMatchDataByMatchId(boolean isHome, String matchId) {
        try {
            Connection conn = OracleUtil.getConnection();
            List<PersonalMatchData> personalMatchDataList = new ArrayList<>();
            String sql = "";
            if (isHome) {
                sql = "SELECT\n" +
                        "  PL.ID,\n" +
                        "  PL.NAME,\n" +
                        "  P.MINUTES,\n" +
                        "  P.FGM,\n" +
                        "  P.FGA,\n" +
                        "  P.FG_PCT,\n" +
                        "  P.FGTHREEM,\n" +
                        "  P.FGTHREEA,\n" +
                        "  P.FGTHREE_PCT,\n" +
                        "  P.FTM,\n" +
                        "  P.FTA,\n" +
                        "  P.FT_PCT,\n" +
                        "  P.OREB,\n" +
                        "  P.DREB,\n" +
                        "  P.REB,\n" +
                        "  P.AST,\n" +
                        "  P.STL,\n" +
                        "  P.BLK,\n" +
                        "  P.TOF,\n" +
                        "  P.PF,\n" +
                        "  P.PTS,\n" +
                        "  P.PLUS_MINS\n" +
                        "FROM MATCH M, TEAM_COMMON T, PLAYERSTAT P, PLAYER PL\n" +
                        "WHERE M.MATCH_ID = P.MATCH_ID\n" +
                        "      AND P.TEAM_ID = M.HOME_ID\n" +
                        "      AND P.PLAYER_ID = PL.ID\n" +
                        "      AND T.TEAM_ID = M.HOME_ID AND M.MATCH_ID = ?";
            } else {
                sql = "SELECT\n" +
                        "  PL.ID,\n" +
                        "  PL.NAME,\n" +
                        "  P.MINUTES,\n" +
                        "  P.FGM,\n" +
                        "  P.FGA,\n" +
                        "  P.FG_PCT,\n" +
                        "  P.FGTHREEM,\n" +
                        "  P.FGTHREEA,\n" +
                        "  P.FGTHREE_PCT,\n" +
                        "  P.FTM,\n" +
                        "  P.FTA,\n" +
                        "  P.FT_PCT,\n" +
                        "  P.OREB,\n" +
                        "  P.DREB,\n" +
                        "  P.REB,\n" +
                        "  P.AST,\n" +
                        "  P.STL,\n" +
                        "  P.BLK,\n" +
                        "  P.TOF,\n" +
                        "  P.PF,\n" +
                        "  P.PTS,\n" +
                        "  P.PLUS_MINS\n" +
                        "FROM MATCH M, TEAM_COMMON T, PLAYERSTAT P, PLAYER PL\n" +
                        "WHERE M.MATCH_ID = P.MATCH_ID\n" +
                        "      AND P.TEAM_ID = M.VISITOR_ID\n" +
                        "      AND P.PLAYER_ID = PL.ID\n" +
                        "      AND T.TEAM_ID = M.VISITOR_ID AND M.MATCH_ID = ?";
            }

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(matchId));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PersonalMatchData personalMatchData = rsToPersonalMatchData(rs);
                personalMatchDataList.add(personalMatchData);
            }
            OracleUtil.stopConnection(conn, pstmt);
            return personalMatchDataList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the total performance statistics of a team(home or visitor) by match id.
     *
     * @param isHome
     * @param matchId
     * @return
     */
    public List<TeamTotalStat> getTeamTotalStatByMatchId(boolean isHome, String matchId) {
        try {
            Connection conn = OracleUtil.getConnection();
            String sql = "";
            if (isHome) {
                sql = "SELECT\n" +
                        "  SUM(P.FGM)        AS FGM,\n" +
                        "  SUM(P.FGA)        AS FGA,\n" +
                        "  SUM(P.FGTHREEM)   AS THREE_M,\n" +
                        "  SUM(P.FGTHREEA)   AS THREE_A,\n" +
                        "  SUM(P.FTM)        AS FTM,\n" +
                        "  SUM(P.FTA)        AS FTA,\n" +
                        "  SUM(P.OREB) AS OREB,\n" +
                        "  SUM(P.DREB) AS DREB,\n" +
                        "  SUM(P.REB) AS REB,\n" +
                        "  SUM(P.AST) AS AST,\n" +
                        "  SUM(P.STL) AS STL,\n" +
                        "  SUM(P.BLK) AS BLK,\n" +
                        "  SUM(P.TOF) AS TOF,\n" +
                        "  SUM(P.PF) AS PF,\n" +
                        "  SUM(P.PTS) AS PTS\n" +
                        "FROM MATCH M, TEAM_COMMON T, PLAYERSTAT P, PLAYER PL\n" +
                        "WHERE M.MATCH_ID = P.MATCH_ID\n" +
                        "      AND P.TEAM_ID = M.HOME_ID\n" +
                        "      AND P.PLAYER_ID = PL.ID\n" +
                        "      AND T.TEAM_ID = M.HOME_ID AND M.MATCH_ID = ?\n" +
                        "GROUP BY T.TEAM_ID";
            } else {
                sql = "SELECT\n" +
                        "  SUM(P.FGM)        AS FGM,\n" +
                        "  SUM(P.FGA)        AS FGA,\n" +
                        "  SUM(P.FGTHREEM)   AS THREE_M,\n" +
                        "  SUM(P.FGTHREEA)   AS THREE_A,\n" +
                        "  SUM(P.FTM)        AS FTM,\n" +
                        "  SUM(P.FTA)        AS FTA,\n" +
                        "  SUM(P.OREB) AS OREB,\n" +
                        "  SUM(P.DREB) AS DREB,\n" +
                        "  SUM(P.REB) AS REB,\n" +
                        "  SUM(P.AST) AS AST,\n" +
                        "  SUM(P.STL) AS STL,\n" +
                        "  SUM(P.BLK) AS BLK,\n" +
                        "  SUM(P.TOF) AS TOF,\n" +
                        "  SUM(P.PF) AS PF,\n" +
                        "  SUM(P.PTS) AS PTS\n" +
                        "FROM MATCH M, TEAM_COMMON T, PLAYERSTAT P, PLAYER PL\n" +
                        "WHERE M.MATCH_ID = P.MATCH_ID\n" +
                        "      AND P.TEAM_ID = M.VISITOR_ID\n" +
                        "      AND P.PLAYER_ID = PL.ID\n" +
                        "      AND T.TEAM_ID = M.VISITOR_ID AND M.MATCH_ID = ?\n" +
                        "GROUP BY T.TEAM_ID";
            }
            PreparedStatement pstmt = conn.prepareStatement(sql);
            List<TeamTotalStat> teamTotalStatList = new ArrayList<>();
            pstmt.setInt(1, Integer.parseInt(matchId));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                TeamTotalStat teamTotalStat = rsToTeamTotalStat(rs);
                teamTotalStatList.add(teamTotalStat);
            }
            OracleUtil.stopConnection(conn, pstmt);
            return teamTotalStatList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private TeamTotalStat rsToTeamTotalStat(ResultSet rs) throws SQLException {
        int fgM = rs.getInt("FGM");
        int fgA = rs.getInt("FGA");
        int fgThreeM = rs.getInt("THREE_M");
        int fgThreeA = rs.getInt("THREE_A");
        int ftM = rs.getInt("FTM");
        int ftA = rs.getInt("FTA");
        int oReb = rs.getInt("OREB");
        int dReb = rs.getInt("DREB");
        int reb = rs.getInt("REB");
        int ast = rs.getInt("AST");
        int stl = rs.getInt("STL");
        int blk = rs.getInt("BLK");
        int tof = rs.getInt("TOF");
        int pf = rs.getInt("PF");
        int pts = rs.getInt("PTS");
        return new TeamTotalStat(fgM, fgA, fgThreeM, fgThreeA, ftM, ftA,
                oReb, dReb, reb, ast, stl, blk, tof, pf, pts);
    }

    private PersonalMatchData rsToPersonalMatchData(ResultSet rs) throws SQLException {
        int playerId = rs.getInt("ID");
        String name = rs.getString("NAME");
        String minutes = rs.getString("MINUTES");
        int fgM = rs.getInt("FGM");
        int fgA = rs.getInt("FGA");
        double fgPct = rs.getDouble("FG_PCT");
        int fgThreeM = rs.getInt("FGTHREEM");
        int fgThreeA = rs.getInt("FGTHREEA");
        double fgThreePct = rs.getDouble("FGTHREE_PCT");
        int ftM = rs.getInt("FTM");
        int ftA = rs.getInt("FTA");
        double ftPct = rs.getDouble("FT_PCT");
        int oReb = rs.getInt("OREB");
        int dReb = rs.getInt("DREB");
        int reb = rs.getInt("REB");
        int ast = rs.getInt("AST");
        int stl = rs.getInt("STL");
        int blk = rs.getInt("BLK");
        int tof = rs.getInt("TOF");
        int pf = rs.getInt("PF");
        int pts = rs.getInt("PTS");
        int eff = rs.getInt("PLUS_MINS");
        return new PersonalMatchData(playerId, name, minutes, fgM, fgA, fgPct,
                fgThreeM, fgThreeA, fgThreePct, ftM, ftA,
                ftPct, oReb, dReb, reb, ast,
                stl, blk, tof, pf, pts, eff);
    }

    private TeamMatchStat rsToTeamMatchStat(ResultSet rs) throws SQLException {
        int homeId = rs.getInt("HOME_ID");
        String homeIcon = rs.getString("HOME_ICON");
        String homeName = rs.getString("HOME_NAME");
        int homeScores = rs.getInt("HOME_SCORES");
        int awayId = rs.getInt("AWAY_ID");
        String awayIcon = rs.getString("AWAY_ICON");
        String awayName = rs.getString("AWAY_NAME");
        int awayScores = rs.getInt("AWAY_SCORES");
        return new TeamMatchStat(homeId, homeIcon, homeName, homeScores, awayId, awayIcon, awayName, awayScores);
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

package com.nba.datacenter.dao;

import com.nba.datacenter.domain.Team;
import com.nba.datacenter.utils.OracleUtil;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Component
public class TeamDao {

    /**
     * Get the information of a team by team id
     *
     * @param teamId
     * @return
     */
    public Team getTeamByTeamId(String teamId) {
        try {
            Connection conn = OracleUtil.getConnection();
            String sql = "SELECT * FROM TEAM_COMMON WHERE TEAM_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            Team team = new Team();
            pstmt.setInt(1, Integer.parseInt(teamId));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                team = rsToTeam(rs);
            }
            OracleUtil.stopConnection(conn, pstmt);
            return team;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get all teams in one conference(west or east)
     *
     * @param s
     * @return
     */
    public List<Team> getTeamByDivision(String s) {
        try {
            Connection conn = OracleUtil.getConnection();
            String sql = "SELECT * FROM TEAM_COMMON WHERE DIVISION = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            List<Team> teamList = new ArrayList<>();
            pstmt.setString(1, s);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Team team = rsToTeam(rs);
                teamList.add(team);
            }
            OracleUtil.stopConnection(conn, pstmt);
            return teamList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get information of a team by the id of its players
     *
     * @param playerId
     * @return
     */
    public Team getTeamByPlayerId(String playerId) {
        try {
            Connection conn = OracleUtil.getConnection();
            String sql = "SELECT * FROM TEAM_COMMON T, PLAYER P " +
                    "WHERE T.ABBREVIATION = P.TEAM AND P.ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            Team team = new Team();
            pstmt.setInt(1, Integer.parseInt(playerId));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                team = rsToTeam(rs);
            }
            OracleUtil.stopConnection(conn, pstmt);
            return team;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Team rsToTeam(ResultSet rs) throws SQLException {
        int id = rs.getInt("TEAM_ID");
        String abbrv = rs.getString("ABBREVIATION");
        String nickname = rs.getString("NICKNAME");
        int yearFounded = rs.getInt("YEARFOUNDED");
        String city = rs.getString("CITY");
        String arena = rs.getString("ARENA");
        String owner = rs.getString("OWNER");
        String division = rs.getString("DIVISION");
        String icon = rs.getString("ICON");
        return new Team(id, abbrv, nickname, yearFounded, city, arena, owner, division, icon);
    }
}

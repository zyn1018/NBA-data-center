package com.nba.datacenter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Match {
    private int matchId;
    private Date matchDate;
    private int homeTeamId;
    private String homeTeamName;
    private int awayTeamId;
    private String awayTeamName;
}

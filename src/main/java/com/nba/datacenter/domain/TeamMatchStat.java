package com.nba.datacenter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TeamMatchStat {
    private int homeId;
    private String homeIcon;
    private String homeName;
    private int homeScores;
    private int awayId;
    private String awayIcon;
    private String awayName;
    private int awayScores;
}

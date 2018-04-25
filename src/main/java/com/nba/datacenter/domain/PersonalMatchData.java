package com.nba.datacenter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PersonalMatchData {
    private int playerId;
    private String name;
    private String minutes;
    private int fgM;
    private int fgA;
    private double fgPct;
    private int fgThreeM;
    private int fgThreeA;
    private double fgThreePct;
    private int ftM;
    private int ftA;
    private double ftPct;
    private int oReb;
    private int dReb;
    private int reb;
    private int ast;
    private int stl;
    private int blk;
    private int tof;
    private int pf;
    private int pts;
    private int eff;
}

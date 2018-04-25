package com.nba.datacenter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TeamTotalStat {
    private int fgM;
    private int fgA;
    private int fgThreeM;
    private int fgThreeA;
    private int ftM;
    private int ftA;
    private int oReb;
    private int dReb;
    private int reb;
    private int ast;
    private int stl;
    private int blk;
    private int tof;
    private int pf;
    private int pts;
}

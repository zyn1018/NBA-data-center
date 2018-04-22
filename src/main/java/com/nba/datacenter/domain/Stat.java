package com.nba.datacenter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stat {
    private int playerId;
    private String name;
    private double points;
    private double reb;
    private double ast;
    private double fgpct;
    private double ftpct;
    private double threepct;
    private double stl;
    private double blk;
    private double eff;
    private String icon;
}

package com.nba.datacenter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    private int teamId;
    private String Abbrev;
    private String nickname;
    private int yearFounded;
    private String city;
    private String arena;
    private String owner;
    private String division;
    private String icon;
}

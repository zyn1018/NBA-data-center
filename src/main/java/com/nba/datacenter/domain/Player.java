package com.nba.datacenter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    private int playerId;
    private String name;
    private String team;
    private int age;
    private int height;
    private int weight;
    private String college;
    private String country;
    private String draftYear;
    private String draftRound;
    private String draftNumber;
}

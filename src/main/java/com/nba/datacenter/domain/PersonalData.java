package com.nba.datacenter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PersonalData {
    private double points;
    private double rebounds;
    private double assists;
    private double blocks;
    private double steals;
    private double turnovers;
}

export class TeamMatchStat {
  constructor(public homeId: number,
              public homeIcon: string,
              public homeName: string,
              public homeScores: number,
              public awayId: number,
              public awayIcon: string,
              public awayName: string,
              public awayScores: number
  ) {
  }
}

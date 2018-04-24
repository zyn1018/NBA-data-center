export class Match {
  constructor(public matchId: number,
              public matchDate: Date,
              public homeTeamId: number,
              public homeTeamName: string,
              public awayTeamId: number,
              public awayTeamName: string
  ) {
  }
}

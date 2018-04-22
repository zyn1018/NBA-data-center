export class Team {
  constructor(public teamId: number,
              public abbrev: string,
              public nickname: string,
              public yearFounded: number,
              public city: string,
              public arena: string,
              public owner: string,
              public division: string,
              public icon: string
  ) {
  }
}

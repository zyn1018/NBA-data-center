import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {MatchService} from "../service/MatchService";
import {TeamMatchStat} from "../domain/TeamMatchStat";
import {MatTableDataSource} from "@angular/material";
import {PersonalMatchStat} from "../domain/PersonalMatchStat";
import {TeamTotalStat} from "../domain/TeamTotalStat";

@Component({
  selector: 'app-match-detail',
  templateUrl: './match-detail.component.html',
  styleUrls: ['./match-detail.component.scss']
})
export class MatchDetailComponent implements OnInit {

  matchId: string;
  teamMatchStat: TeamMatchStat;
  // teamTotalStatHome: TeamTotalStat;
  // teamTotalStatAway: TeamTotalStat;
  dataSourceHome: MatTableDataSource<PersonalMatchStat[]>;
  dataSourceAway: MatTableDataSource<PersonalMatchStat[]>;
  dataSourceTeamHome: MatTableDataSource<TeamTotalStat[]>;
  dataSourceTeamAway: MatTableDataSource<TeamTotalStat[]>;
  displayedColumns = ['PLAYER', 'MIN', 'FGM', 'FGA', 'FG%', '3PM'
    , '3PA', '3P%', 'FTM', 'FTA', 'FT%', 'OREB', 'DREB', 'REB', 'AST',
    'TOV', 'STL', 'BLK', 'PF', 'PTS', '+/-'];
  displayedColumnsTeam = ['TOTAL', 'FGM', 'FGA', 'FG%', '3PM'
    , '3PA', '3P%', 'FTM', 'FTA', 'FT%', 'OREB', 'DREB', 'REB', 'AST',
    'TOV', 'STL', 'BLK', 'PF', 'PTS', '+/-'];

  constructor(private router: Router,
              private matchService: MatchService) {
  }

  ngOnInit() {
    this.matchId = this.router.url.split("/")[2];
    this.matchService.getTeamMatchStatByMatchId(this.matchId).subscribe(data => {
      this.teamMatchStat = data;
    });
    this.matchService.getHomePlayerMatchDataByMatchId(this.matchId).subscribe(data => {
      this.dataSourceHome = new MatTableDataSource<PersonalMatchStat[]>(data);
    });
    this.matchService.getAwayPlayerMatchDataByMatchId(this.matchId).subscribe(data => {
      this.dataSourceAway = new MatTableDataSource<PersonalMatchStat[]>(data);
    });
    this.matchService.getHomeTeamTotalStatByMatchId(this.matchId).subscribe(data => {
      this.dataSourceTeamHome = new MatTableDataSource<TeamTotalStat[]>(data);
    });
    this.matchService.getAwayTeamTotalStatByMatchId(this.matchId).subscribe(data => {
      this.dataSourceTeamAway = new MatTableDataSource<TeamTotalStat[]>(data);
    });
  }

  goToTeamDetail(teamId: number) {
    this.router.navigateByUrl('/team/' + teamId);
  }

  goToPlayerDetail(playerId: number) {
    this.router.navigateByUrl('/player/' + playerId);
  }
}

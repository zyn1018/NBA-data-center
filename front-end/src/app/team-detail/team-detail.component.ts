import {Component, OnInit} from '@angular/core';
import {TeamService} from "../service/TeamService";
import {Team} from "../domain/Team";
import {Router} from "@angular/router";
import {Player} from "../domain/Player";
import {PlayerService} from "../service/PlayerService";

@Component({
  selector: 'app-team-detail',
  templateUrl: './team-detail.component.html',
  styleUrls: ['./team-detail.component.scss']
})
export class TeamDetailComponent implements OnInit {
  private team: Team;
  private teamId: string;
  private roster: Player[];
  private rosterUrl = '/roster/';
  private playerDetailUrl = '/player/';

  constructor(private teamService: TeamService,
              private playerService: PlayerService,
              private router: Router) {
  }

  ngOnInit() {
    this.teamId = this.router.url.split("/")[2];
    this.teamService.getTeamById(this.teamId).subscribe(data => {
      this.team = data;
    });
    this.playerService.getTeamRosterByTeamId(this.teamId).subscribe(data => {
      this.roster = data;
    })
  }

  goToPlayerDetail(playerId: number) {
    this.router.navigateByUrl(this.playerDetailUrl + playerId);
  }

}

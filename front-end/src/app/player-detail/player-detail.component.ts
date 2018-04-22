import {Component, OnInit} from '@angular/core';
import {PlayerService} from "../service/PlayerService";
import {Router} from "@angular/router";
import {Player} from "../domain/Player";
import {TeamService} from "../service/TeamService";
import {Team} from "../domain/Team";

@Component({
  selector: 'app-player-detail',
  templateUrl: './player-detail.component.html',
  styleUrls: ['./player-detail.component.scss']
})
export class PlayerDetailComponent implements OnInit {
  private playerId: string;
  private player: Player;
  private height: string;
  private team: Team;

  constructor(private playerService: PlayerService,
              private teamService: TeamService,
              private router: Router) {
  }

  ngOnInit() {
    this.playerId = this.router.url.split('/')[2];
    this.playerService.getPlayerById(this.playerId).subscribe(
      data => {
        this.player = data;
        this.height = '' + Math.floor(this.player.height / 12) + '-' + this.player.height % 12;
      }
    );
    this.teamService.getTeamByPlayerId(this.playerId).subscribe(data => {
      this.team = data;
    })
  }

  goToTeamDetail(teamId: number) {
    this.router.navigateByUrl('/team/' + teamId);
  }
}

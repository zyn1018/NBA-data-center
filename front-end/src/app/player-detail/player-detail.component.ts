import {Component, OnInit} from '@angular/core';
import {PlayerService} from "../service/PlayerService";
import {Router} from "@angular/router";
import {Player} from "../domain/Player";
import {TeamService} from "../service/TeamService";
import {Team} from "../domain/Team";
import {PersonalStat} from "../domain/PersonalStat";
import {StatService} from "../service/StatService";
import {HttpClient} from "@angular/common/http";

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
  private personalStat: PersonalStat;
  private imageUrl: string;

  constructor(private playerService: PlayerService,
              private teamService: TeamService,
              private statService: StatService,
              private router: Router,
              private http: HttpClient) {
  }

  ngOnInit() {
    this.playerId = this.router.url.split('/')[2];
    this.playerService.getPlayerById(this.playerId).subscribe(
      data => {
        this.player = data;
        this.height = '' + Math.floor(this.player.height / 12) + ' ft ' + this.player.height % 12 + '″';
        this.imageUrl = 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/' + this.player.playerId + '.png';
      }
    );

    this.teamService.getTeamByPlayerId(this.playerId).subscribe(data => {
      this.team = data;
    });
    this.statService.getPersonalStatByPlayerId(this.playerId).subscribe(data => {
      this.personalStat = data;
    })
  }

  goToTeamDetail(teamId: number) {
    this.router.navigateByUrl('/team/' + teamId);
  }
}

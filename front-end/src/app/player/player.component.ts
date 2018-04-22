import {Component, OnInit} from '@angular/core';
import {Player} from "../domain/Player";
import {PlayerService} from "../service/PlayerService";
import {Router} from "@angular/router";

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.scss']
})
export class PlayerComponent implements OnInit {
  private playerDetailUrl = '/player/';
  playersA: Player[];
  playersB: Player[];
  playersC: Player[];
  playersD: Player[];
  playersE: Player[];
  playersF: Player[];
  playersG: Player[];
  playersH: Player[];
  playersI: Player[];
  playersJ: Player[];
  playersK: Player[];
  playersL: Player[];
  playersM: Player[];
  playersN: Player[];
  playersO: Player[];
  playersP: Player[];
  playersQ: Player[];
  playersR: Player[];
  playersS: Player[];
  playersT: Player[];
  playersU: Player[];
  playersV: Player[];
  playersW: Player[];
  playersX: Player[];
  playersY: Player[];
  playersZ: Player[];

  constructor(private playerService: PlayerService,
              private router: Router) {
  }

  ngOnInit() {
    this.playerService.getPlayersByInitial('a').subscribe(data => {
      this.playersA = data;
    });
    this.playerService.getPlayersByInitial('b').subscribe(data => {
      this.playersB = data;
    });
    this.playerService.getPlayersByInitial('c').subscribe(data => {
      this.playersC = data;
    });
    this.playerService.getPlayersByInitial('d').subscribe(data => {
      this.playersD = data;
    });
    this.playerService.getPlayersByInitial('e').subscribe(data => {
      this.playersE = data;
    });
    this.playerService.getPlayersByInitial('f').subscribe(data => {
      this.playersF = data;
    });
    this.playerService.getPlayersByInitial('g').subscribe(data => {
      this.playersG = data;
    });
    this.playerService.getPlayersByInitial('h').subscribe(data => {
      this.playersH = data;
    });
    this.playerService.getPlayersByInitial('i').subscribe(data => {
      this.playersI = data;
    });
    this.playerService.getPlayersByInitial('j').subscribe(data => {
      this.playersJ = data;
    });
    this.playerService.getPlayersByInitial('k').subscribe(data => {
      this.playersK = data;
    });
    this.playerService.getPlayersByInitial('l').subscribe(data => {
      this.playersL = data;
    });
    this.playerService.getPlayersByInitial('m').subscribe(data => {
      this.playersM = data;
    });
    this.playerService.getPlayersByInitial('n').subscribe(data => {
      this.playersN = data;
    });
    this.playerService.getPlayersByInitial('o').subscribe(data => {
      this.playersO = data;
    });
    this.playerService.getPlayersByInitial('p').subscribe(data => {
      this.playersP = data;
    });
    this.playerService.getPlayersByInitial('q').subscribe(data => {
      this.playersQ = data;
    });
    this.playerService.getPlayersByInitial('r').subscribe(data => {
      this.playersR = data;
    });
    this.playerService.getPlayersByInitial('s').subscribe(data => {
      this.playersS = data;
    });
    this.playerService.getPlayersByInitial('t').subscribe(data => {
      this.playersT = data;
    });
    this.playerService.getPlayersByInitial('u').subscribe(data => {
      this.playersU = data;
    });
    this.playerService.getPlayersByInitial('v').subscribe(data => {
      this.playersV = data;
    });
    this.playerService.getPlayersByInitial('w').subscribe(data => {
      this.playersW = data;
    });
    this.playerService.getPlayersByInitial('x').subscribe(data => {
      this.playersX = data;
    });
    this.playerService.getPlayersByInitial('y').subscribe(data => {
      this.playersY = data;
    });
    this.playerService.getPlayersByInitial('z').subscribe(data => {
      this.playersZ = data;
    });
  }

  goToPlayerDetail(playerId: number) {
    this.router.navigateByUrl(this.playerDetailUrl + playerId);
  }
}

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
  loadedA = false;
  loadedB = false;
  loadedC = false;
  loadedD = false;
  loadedE = false;
  loadedF = false;
  loadedG = false;
  loadedH = false;
  loadedI = false;
  loadedJ = false;
  loadedK = false;
  loadedL = false;
  loadedM = false;
  loadedN = false;
  loadedO = false;
  loadedP = false;
  loadedQ = false;
  loadedR = false;
  loadedS = false;
  loadedT = false;
  loadedU = false;
  loadedV = false;
  loadedW = false;
  loadedX = false;
  loadedY = false;
  loadedZ = false;

  constructor(private playerService: PlayerService,
              private router: Router) {
  }

  /**
   * Players are sorted by the first letter of their initials
   */
  ngOnInit() {
    this.playerService.getPlayersByInitial('a').subscribe(data => {
      this.playersA = data;
      this.loadedA = true;
    });
    this.playerService.getPlayersByInitial('b').subscribe(data => {
      this.playersB = data;
      this.loadedB = true;
    });
    this.playerService.getPlayersByInitial('c').subscribe(data => {
      this.playersC = data;
      this.loadedC = true;
    });
    this.playerService.getPlayersByInitial('d').subscribe(data => {
      this.playersD = data;
      this.loadedD = true;
    });
    this.playerService.getPlayersByInitial('e').subscribe(data => {
      this.playersE = data;
      this.loadedE = true;
    });
    this.playerService.getPlayersByInitial('f').subscribe(data => {
      this.playersF = data;
      this.loadedF = true;
    });
    this.playerService.getPlayersByInitial('g').subscribe(data => {
      this.playersG = data;
      this.loadedG = true;
    });
    this.playerService.getPlayersByInitial('h').subscribe(data => {
      this.playersH = data;
      this.loadedH = true;
    });
    this.playerService.getPlayersByInitial('i').subscribe(data => {
      this.playersI = data;
      this.loadedI = true;
    });
    this.playerService.getPlayersByInitial('j').subscribe(data => {
      this.playersJ = data;
      this.loadedJ = true;
    });
    this.playerService.getPlayersByInitial('k').subscribe(data => {
      this.playersK = data;
      this.loadedK = true;
    });
    this.playerService.getPlayersByInitial('l').subscribe(data => {
      this.playersL = data;
      this.loadedL = true;
    });
    this.playerService.getPlayersByInitial('m').subscribe(data => {
      this.playersM = data;
      this.loadedM = true;
    });
    this.playerService.getPlayersByInitial('n').subscribe(data => {
      this.playersN = data;
      this.loadedN = true;
    });
    this.playerService.getPlayersByInitial('o').subscribe(data => {
      this.playersO = data;
      this.loadedO = true;
    });
    this.playerService.getPlayersByInitial('p').subscribe(data => {
      this.playersP = data;
      this.loadedP = true;
    });
    this.playerService.getPlayersByInitial('q').subscribe(data => {
      this.playersQ = data;
      this.loadedQ = true;
    });
    this.playerService.getPlayersByInitial('r').subscribe(data => {
      this.playersR = data;
      this.loadedR = true;
    });
    this.playerService.getPlayersByInitial('s').subscribe(data => {
      this.playersS = data;
      this.loadedS = true;
    });
    this.playerService.getPlayersByInitial('t').subscribe(data => {
      this.playersT = data;
      this.loadedT = true;
    });
    this.playerService.getPlayersByInitial('u').subscribe(data => {
      this.playersU = data;
      this.loadedU = true;
    });
    this.playerService.getPlayersByInitial('v').subscribe(data => {
      this.playersV = data;
      this.loadedV = true;
    });
    this.playerService.getPlayersByInitial('w').subscribe(data => {
      this.playersW = data;
      this.loadedW = true;
    });
    this.playerService.getPlayersByInitial('x').subscribe(data => {
      this.playersX = data;
      this.loadedX = true;
    });
    this.playerService.getPlayersByInitial('y').subscribe(data => {
      this.playersY = data;
      this.loadedY = true;
    });
    this.playerService.getPlayersByInitial('z').subscribe(data => {
      this.playersZ = data;
      this.loadedZ = true;
    });
  }

  goToPlayerDetail(playerId: number) {
    this.router.navigateByUrl(this.playerDetailUrl + playerId);
  }
}

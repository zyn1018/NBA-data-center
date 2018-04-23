import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {RankVo} from "../vo/RankVo";
import {PlayerService} from "../service/PlayerService";
import {StatService} from "../service/StatService";
import {TableRecords} from "../domain/TableRecords";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  private playerDetailUrl = '/player/';
  private top5PtsRank: RankVo[];
  private top5RebRank: RankVo[];
  private top5AstRank: RankVo[];
  private top5BlkRank: RankVo[];
  private tableRecords: TableRecords[];

  constructor(private router: Router,
              private playerService: PlayerService,
              private statService: StatService) {
  }

  ngOnInit() {
    this.playerService.getTop5PlayersByRankType('pts').subscribe(data => {
      this.top5PtsRank = data;
    });
    this.playerService.getTop5PlayersByRankType('reb').subscribe(data => {
      this.top5RebRank = data;
    });
    this.playerService.getTop5PlayersByRankType('ast').subscribe(data => {
      this.top5AstRank = data;
    });
    this.playerService.getTop5PlayersByRankType('blk').subscribe(data => {
      this.top5BlkRank = data;
    });
  }

  goToRankDetail(option: string) {
    switch (option) {
      case 'points':
        this.router.navigateByUrl('/rank/points');
        break;
      case 'rebounds':
        this.router.navigateByUrl('/rank/rebounds');
        break;
      case 'assists':
        this.router.navigateByUrl('/rank/assists');
        break;
      case 'blocks':
        this.router.navigateByUrl('/rank/blocks');
        break;
    }
  }

  goToPlayerDetail(playerId: number) {
    this.router.navigateByUrl(this.playerDetailUrl + playerId);
  }

  getTableRecordsNum() {
    this.statService.getTableRecordsNum().subscribe(data => {
      this.tableRecords = data;
      console.log(data);
    })
  }


}

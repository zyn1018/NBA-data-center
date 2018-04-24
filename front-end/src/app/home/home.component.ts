import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {RankVo} from "../vo/RankVo";
import {TableRecord} from "../domain/TableRecord";
import {PlayerService} from "../service/PlayerService";
import {StatService} from "../service/StatService";


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
  tableRecords: TableRecord[];
  private total: number;
  private dataLoaded1 = false;
  private dataLoaded2 = false;
  private dataLoaded3 = false;
  private dataLoaded4 = false;

  constructor(private router: Router,
              private playerService: PlayerService,
              private statService: StatService) {
  }

  ngOnInit() {
    this.playerService.getTop5PlayersByRankType('pts').subscribe(data => {
      this.top5PtsRank = data;
      this.dataLoaded1 = true;
    });
    this.playerService.getTop5PlayersByRankType('reb').subscribe(data => {
      this.top5RebRank = data;
      this.dataLoaded2 = true;
    });
    this.playerService.getTop5PlayersByRankType('ast').subscribe(data => {
      this.top5AstRank = data;
      this.dataLoaded3 = true;
    });
    this.playerService.getTop5PlayersByRankType('blk').subscribe(data => {
      this.top5BlkRank = data;
      this.dataLoaded4 = true;
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
      this.total = 0;
      this.tableRecords.forEach(item => {
        this.total += item.numRows;
      });
    });
  }


}

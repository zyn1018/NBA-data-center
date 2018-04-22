import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {StatService} from "../service/StatService";
import {Router} from "@angular/router";
import {MatPaginator, MatTableDataSource} from "@angular/material";

@Component({
  selector: 'app-rank-detail',
  templateUrl: './rank-detail.component.html',
  styleUrls: ['./rank-detail.component.scss']
})
export class RankDetailComponent implements OnInit, AfterViewInit {

  displayedColumns = ['PLAYER', 'TEAM', 'POINTS', 'REB', 'AST', 'FG%'
    , 'FT%', '3P%', 'STL', 'BLK', 'EFF'];

  title: string;
  dataSource: MatTableDataSource;
  rankType: string;
  playerDetailUrl = '/player/';

  constructor(private router: Router,
              private statService: StatService) {
  }

  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit() {
    this.rankType = this.router.url.split("/")[2];
    this.statService.getStatRankByRankType(this.rankType).subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
    });

    switch (this.rankType) {
      case 'points':
        this.title = 'Point Ranking';
        break;
      case 'rebounds':
        this.title = 'Rebound Ranking';
        break;
      case 'assists':
        this.title = 'Assist Ranking';
        break;
      case 'blocks':
        this.title = 'Block Ranking';
        break;
    }
  }

  goToPlayerDetail(playerId: number) {
    this.router.navigateByUrl(this.playerDetailUrl + playerId);
  }
}

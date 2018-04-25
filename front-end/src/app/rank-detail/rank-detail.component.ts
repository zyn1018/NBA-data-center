import {Component, OnInit, ViewChild} from '@angular/core';
import {StatService} from "../service/StatService";
import {Router} from "@angular/router";
import {MatPaginator, MatTableDataSource} from "@angular/material";
import {Stat} from "../domain/Stat";

@Component({
  selector: 'app-rank-detail',
  templateUrl: './rank-detail.component.html',
  styleUrls: ['./rank-detail.component.scss']
})
export class RankDetailComponent implements OnInit {

  displayedColumns = ['PLAYER', 'TEAM', 'POINTS', 'REB', 'AST', 'FG%'
    , 'FT%', '3P%', 'STL', 'BLK', '+/-'];

  title: string;
  dataSource: MatTableDataSource<Stat[]>;
  private paginator: MatPaginator;
  rankType: string;
  playerDetailUrl = '/player/';
  dataLoaded = false;

  constructor(private router: Router,
              private statService: StatService) {
  }

  @ViewChild(MatPaginator) set matPaginator(mp: MatPaginator) {
    this.paginator = mp;
    this.setDataSourceAttributes();
  }

  setDataSourceAttributes() {
    if (this.dataSource) {
      this.dataSource.paginator = this.paginator;
    }
    if (this.paginator) {
      this.applyFilter('');
    }
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // Datasource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }

  ngOnInit() {
    this.rankType = this.router.url.split("/")[2];
    this.statService.getStatRankByRankType(this.rankType).subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataLoaded = true;
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

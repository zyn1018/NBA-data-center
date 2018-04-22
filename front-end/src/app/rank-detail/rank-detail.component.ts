import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-rank-detail',
  templateUrl: './rank-detail.component.html',
  styleUrls: ['./rank-detail.component.scss']
})
export class RankDetailComponent implements OnInit {

  displayedColumns = ['PLAYER', 'TEAM', 'POINTS', 'REB', 'AST', 'FG%'
    , 'FT%', '3P%', 'STL', 'BLK', 'EFF'];

  constructor() {
  }

  ngOnInit() {
  }


}

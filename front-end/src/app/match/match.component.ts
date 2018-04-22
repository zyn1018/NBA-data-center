import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-match',
  templateUrl: './match.component.html',
  styleUrls: ['./match.component.scss']
})
export class MatchComponent implements OnInit {

  displayedColumns = ['Date', 'Home', 'Away'];

  constructor() {
  }

  ngOnInit() {
  }

}

import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private router: Router) {
  }

  ngOnInit() {
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

}

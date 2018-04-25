import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {MatchService} from "../service/MatchService";
import {Router} from "@angular/router";
import {Match} from "../domain/Match";
import {MatPaginator, MatTableDataSource} from "@angular/material";

@Component({
  selector: 'app-match',
  templateUrl: './match.component.html',
  styleUrls: ['./match.component.scss']
})
export class MatchComponent implements OnInit {

  formModel: FormGroup;
  displayedColumns = ['Date', 'Home', 'VS', 'Away', 'Box Score'];
  dataSource: MatTableDataSource<Match[]>;
  dataIsLoaded = false;
  private paginator: MatPaginator;
  searchLoaded = true;

  constructor(private matchService: MatchService,
              private router: Router) {
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
    const fb = new FormBuilder();
    this.formModel = fb.group(
      {
        startDate: [''],
        endDate: ['']
      }
    );

    this.matchService.getLastMonthMatches().subscribe(data => {
      this.dataIsLoaded = true;
      this.dataSource = new MatTableDataSource(data);

    });
  }

  // static checkDates(formModel: FormGroup) {
  //   if (formModel.controls.endDate.value < formModel.controls.startDate.value) {
  //     return {notValid: true};
  //   }
  //   return null;
  // }

  search() {
    this.searchLoaded = false;
    if (this.formModel.value.startDate == '' || this.formModel.value.endDate == '') {
      this.matchService.getLastMonthMatches().subscribe(data => {
        this.dataSource.data = data;
        this.searchLoaded = true;
      })
    } else {
      this.matchService.getMatchesByDates(this.formModel.value.startDate.toLocaleDateString(),
        this.formModel.value.endDate.toLocaleDateString()).subscribe(data => {
        this.dataSource.data = data;
        this.searchLoaded = true;
      });
    }
  }

  goToTeamDetail(teamId: number) {
    this.router.navigateByUrl('/team/' + teamId);
  }

  goToMatchDetail(matchId: number) {
    this.router.navigateByUrl('/match/' + matchId);
  }
}

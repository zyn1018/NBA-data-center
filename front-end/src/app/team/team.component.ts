import {Component, OnInit} from '@angular/core';
import {Team} from "../domain/Team";
import {TeamService} from "../service/TeamService";
import {Router} from "@angular/router";

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.scss']
})
export class TeamComponent implements OnInit {
  teamsW: Team[];
  teamsE: Team[];
  loadedW = false;
  loadedE = false;


  constructor(private teamService: TeamService,
              private router: Router) {

  }

  ngOnInit() {
    this.teamService.getTeamByDivision('w').subscribe(data => {
      this.teamsW = data;
      this.loadedW = true;
    });
    this.teamService.getTeamByDivision('e').subscribe(data => {
      this.teamsE = data;
      this.loadedE = true;
    });
  }

  goToTeamDetail(teamId: number) {
    this.router.navigateByUrl('/team/' + teamId);
  }
}

import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class TeamService {
  private getTeamByIdUrl = '/api/team/';
  private getTeamsByDivisionUrl = '/api/teams/';
  private getTeamByPlayerIdUrl = '/api/team_via_playerid/';

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  constructor(private http: HttpClient) {
  }

  public getTeamById(id: string): Observable<any> {
    return this.http.get(this.getTeamByIdUrl + id, this.httpOptions);
  }

  public getTeamByDivision(division: string): Observable<any> {
    return this.http.get(this.getTeamsByDivisionUrl + division, this.httpOptions);
  }

  public getTeamByPlayerId(playerId: string): Observable<any> {
    return this.http.get(this.getTeamByPlayerIdUrl + playerId, this.httpOptions);
  }


}

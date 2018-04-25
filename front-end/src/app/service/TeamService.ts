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

  /**
   * Get the information of a team by its team id
   * @param {string} id
   * @returns {Observable<any>}
   */
  public getTeamById(id: string): Observable<any> {
    return this.http.get(this.getTeamByIdUrl + id, this.httpOptions);
  }

  /**
   * Get all teams in one(east or west) conference
   * @param {string} division
   * @returns {Observable<any>}
   */
  public getTeamByDivision(division: string): Observable<any> {
    return this.http.get(this.getTeamsByDivisionUrl + division, this.httpOptions);
  }

  /**
   * Get information of a team by the player id of a player who belongs to that team.
   * @param {string} playerId
   * @returns {Observable<any>}
   */
  public getTeamByPlayerId(playerId: string): Observable<any> {
    return this.http.get(this.getTeamByPlayerIdUrl + playerId, this.httpOptions);
  }


}

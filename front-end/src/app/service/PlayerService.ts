import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class PlayerService {
  private playerListByInitialUrl = '/api/players/';
  private playerByIdUrl = '/api/player/';
  private getTeamRosterByIdUrl = '/api/roster/';
  private getTop5PlayersByRankTypeUrl = '/api/top5rank/';

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  constructor(private http: HttpClient) {
  }

  /**
   * Get players' information by the first letter of their initials
   * @param {string} initial
   * @returns {Observable<any>}
   */
  public getPlayersByInitial(initial: string): Observable<any> {
    return this.http.get(this.playerListByInitialUrl + initial, this.httpOptions);
  }

  /**
   * Get the information of a player by his id
   * @param {string} id
   * @returns {Observable<any>}
   */
  public getPlayerById(id: string): Observable<any> {
    return this.http.get(this.playerByIdUrl + id, this.httpOptions);
  }

  /**
   * Get the roster of a team by its team id
   * @param {string} teamId
   * @returns {Observable<any>}
   */
  public getTeamRosterByTeamId(teamId: string): Observable<any> {
    return this.http.get(this.getTeamRosterByIdUrl + teamId, this.httpOptions);
  }

  /**
   * Get the 5 players with best performance in terms of a certain ranking;
   * @param {string} rankType
   * @returns {Observable<any>}
   */
  public getTop5PlayersByRankType(rankType: string): Observable<any> {
    return this.http.get(this.getTop5PlayersByRankTypeUrl + rankType, this.httpOptions);
  }
}

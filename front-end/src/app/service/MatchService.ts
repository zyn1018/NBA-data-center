import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class MatchService {
  private getAllMatchesUrl = '/api/matches';
  private getMatchesByDatesUrl = '/api/match_dates';
  private getTeamMatchStatByMatchIdUrl = '/api/match/';
  private getPlayerMatchDataByMatchIdUrl = '/api/player_data/';
  private getTeamTotalStatByMatchIdUrl = '/api/team_data/';

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  constructor(private http: HttpClient) {
  }

  /**
   * Get all games played in the last month
   * @returns {Observable<any>}
   */
  public getLastMonthMatches(): Observable<any> {
    return this.http.get(this.getAllMatchesUrl, this.httpOptions);
  }

  /**
   * Get all games played in a certain period of time
   * @param {string} startDate
   * @param {string} endDate
   * @returns {Observable<any>}
   */
  public getMatchesByDates(startDate: string, endDate: string): Observable<any> {
    return this.http.get(this.getMatchesByDatesUrl, {
      params: {
        startDate: startDate,
        endDate: endDate
      }
    });
  }

  /**
   * Get teams' name, icon, scores of a match
   * @param {string} matchId
   * @returns {Observable<any>}
   */
  public getTeamMatchStatByMatchId(matchId: string): Observable<any> {
    return this.http.get(this.getTeamMatchStatByMatchIdUrl + matchId);
  }

  /**
   * Get all players' performance data of the home team
   * @param {string} matchId
   * @returns {Observable<any>}
   */
  public getHomePlayerMatchDataByMatchId(matchId: string): Observable<any> {
    return this.http.get(this.getPlayerMatchDataByMatchIdUrl + matchId, {
      params: {
        isHome: 'true'
      }
    });
  }

  /**
   * Get all players' performance data of the visitor team
   * @param {string} matchId
   * @returns {Observable<any>}
   */
  public getAwayPlayerMatchDataByMatchId(matchId: string): Observable<any> {
    return this.http.get(this.getPlayerMatchDataByMatchIdUrl + matchId, {
      params: {
        isHome: 'false'
      }
    });
  }

  /**
   * Get the total home team stat of a match
   * @param {string} matchId
   * @returns {Observable<any>}
   */
  public getHomeTeamTotalStatByMatchId(matchId: string): Observable<any> {
    return this.http.get(this.getTeamTotalStatByMatchIdUrl + matchId, {
      params: {
        isHome: 'true'
      }
    });
  }

  /**
   * Get the total visitor team stat of a match
   * @param {string} matchId
   * @returns {Observable<any>}
   */
  public getAwayTeamTotalStatByMatchId(matchId: string): Observable<any> {
    return this.http.get(this.getTeamTotalStatByMatchIdUrl + matchId, {
      params: {
        isHome: 'false'
      }
    });
  }
}

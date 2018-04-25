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

  public getLastMonthMatches(): Observable<any> {
    return this.http.get(this.getAllMatchesUrl, this.httpOptions);
  }

  public getMatchesByDates(startDate: string, endDate: string): Observable<any> {
    return this.http.get(this.getMatchesByDatesUrl, {
      params: {
        startDate: startDate,
        endDate: endDate
      }
    });
  }

  public getTeamMatchStatByMatchId(matchId: string): Observable<any> {
    return this.http.get(this.getTeamMatchStatByMatchIdUrl + matchId);
  }

  public getHomePlayerMatchDataByMatchId(matchId: string): Observable<any> {
    return this.http.get(this.getPlayerMatchDataByMatchIdUrl + matchId, {
      params: {
        isHome: 'true'
      }
    });
  }

  public getAwayPlayerMatchDataByMatchId(matchId: string): Observable<any> {
    return this.http.get(this.getPlayerMatchDataByMatchIdUrl + matchId, {
      params: {
        isHome: 'false'
      }
    });
  }

  public getHomeTeamTotalStatByMatchId(matchId: string): Observable<any> {
    return this.http.get(this.getTeamTotalStatByMatchIdUrl + matchId, {
      params: {
        isHome: 'true'
      }
    });
  }

  public getAwayTeamTotalStatByMatchId(matchId: string): Observable<any> {
    return this.http.get(this.getTeamTotalStatByMatchIdUrl + matchId, {
      params: {
        isHome: 'false'
      }
    });
  }
}

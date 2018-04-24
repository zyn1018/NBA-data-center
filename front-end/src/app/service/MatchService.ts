import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Match} from "../domain/Match";

@Injectable()
export class MatchService {
  private getAllMatchesUrl = '/api/matches';
  private getMatchesByDatesUrl = '/api/match_dates';

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
}

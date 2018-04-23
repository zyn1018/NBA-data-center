import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class StatService {
  private getStatRankByRankTypeUrl = '/api/rank/';
  private getPersonalStatByPlayerIdUrl = '/api/personal_data/';
  private getTablesRecordsUrl = '/api/tables';

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  constructor(private http: HttpClient) {
  }

  public getStatRankByRankType(rankType: string): Observable<any> {
    return this.http.get(this.getStatRankByRankTypeUrl + rankType, this.httpOptions);
  }

  public getPersonalStatByPlayerId(playerId: string): Observable<any> {
    return this.http.get(this.getPersonalStatByPlayerIdUrl + playerId, this.httpOptions);
  }

  public getTableRecordsNum(): Observable<any> {
    return this.http.get(this.getTablesRecordsUrl, this.httpOptions);
  }
}

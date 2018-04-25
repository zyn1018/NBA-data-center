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

  /**
   * Get all player's performance data by ranking type(e.g., points, rebounds, etc.)
   * @param {string} rankType
   * @returns {Observable<any>}
   */
  public getStatRankByRankType(rankType: string): Observable<any> {
    return this.http.get(this.getStatRankByRankTypeUrl + rankType, this.httpOptions);
  }

  /**
   * Get the performance data of one player by his player id;
   * @param {string} playerId
   * @returns {Observable<any>}
   */
  public getPersonalStatByPlayerId(playerId: string): Observable<any> {
    return this.http.get(this.getPersonalStatByPlayerIdUrl + playerId, this.httpOptions);
  }

  /**
   * Get the number of rows in the database
   * @returns {Observable<any>}
   */
  public getTableRecordsNum(): Observable<any> {
    return this.http.get(this.getTablesRecordsUrl, this.httpOptions);
  }
}

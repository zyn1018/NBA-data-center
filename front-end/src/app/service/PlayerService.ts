import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class PlayerService {
  private playerListByInitialUrl = '/api/players/';
  private playerByIdUrl = '/api/player/';
  private getTeamRosterByIdUrl = '/api/roster/';

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  constructor(private http: HttpClient) {
  }

  public getPlayersByInitial(initial: string): Observable<any> {
    return this.http.get(this.playerListByInitialUrl + initial, this.httpOptions);
  }

  public getPlayerById(id: string): Observable<any> {
    return this.http.get(this.playerByIdUrl + id, this.httpOptions);
  }


  public getTeamRosterByTeamId(teamId: string): Observable<any> {
    return this.http.get(this.getTeamRosterByIdUrl + teamId, this.httpOptions);
  }
}

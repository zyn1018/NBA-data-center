import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {RankDetailComponent} from "./rank-detail/rank-detail.component";
import {TeamComponent} from "./team/team.component";
import {PlayerComponent} from "./player/player.component";
import {MatchComponent} from "./match/match.component";
import {TeamDetailComponent} from "./team-detail/team-detail.component";
import {PlayerDetailComponent} from "./player-detail/player-detail.component";
import {MatchDetailComponent} from "./match-detail/match-detail.component";

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'rank/points', component: RankDetailComponent},
  {path: 'rank/rebounds', component: RankDetailComponent},
  {path: 'rank/assists', component: RankDetailComponent},
  {path: 'rank/blocks', component: RankDetailComponent},
  {path: 'teams', component: TeamComponent},
  {path: 'players', component: PlayerComponent},
  {path: 'matches', component: MatchComponent},
  {path: 'team/:teamId', component: TeamDetailComponent},
  {path: 'player/:playerId', component: PlayerDetailComponent},
  {path: 'match/:matchId', component: MatchDetailComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

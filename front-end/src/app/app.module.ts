import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {CoreModule} from "./core/core.module";
import {AppRoutingModule} from "./app-routing-module";
import {HttpClientModule} from "@angular/common/http";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {HomeComponent} from './home/home.component';
import {RankDetailComponent} from './rank-detail/rank-detail.component';
import {RouterModule} from "@angular/router";
import {TeamComponent} from './team/team.component';
import {PlayerComponent} from './player/player.component';
import {MatchComponent} from './match/match.component';
import {ReactiveFormsModule} from "@angular/forms";
import {TeamDetailComponent} from './team-detail/team-detail.component';
import {PlayerDetailComponent} from './player-detail/player-detail.component';
import {MatchDetailComponent} from './match-detail/match-detail.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RankDetailComponent,
    TeamComponent,
    PlayerComponent,
    MatchComponent,
    TeamDetailComponent,
    PlayerDetailComponent,
    MatchDetailComponent
  ],
  imports: [
    BrowserModule,
    CoreModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}

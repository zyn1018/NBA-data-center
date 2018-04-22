import {NgModule, Optional, SkipSelf} from '@angular/core';
import {SharedModule} from "../shared/shared.module";
import {HeaderComponent} from './header/header.component';
import {FooterComponent} from './footer/footer.component';
import {RouterModule} from "@angular/router";
import {PlayerService} from "../service/PlayerService";
import {TeamService} from "../service/TeamService";


@NgModule({
  imports: [
    SharedModule,
    RouterModule

  ],
  declarations: [HeaderComponent, FooterComponent],
  exports: [
    HeaderComponent,
    FooterComponent,
    SharedModule
  ],
  providers: [PlayerService, TeamService]
})
export class CoreModule {
  constructor(@Optional() @SkipSelf() parent: CoreModule) {
    if (parent) {
      throw new Error('Module already exists');
    }
  }
}

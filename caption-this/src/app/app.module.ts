import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HowitworksComponent } from './howitworks/howitworks.component';
import { WatchlecturesComponent } from './watchlectures/watchlectures.component';
import { UploadvidsComponent } from './uploadvids/uploadvids.component';
import { ProfileComponent } from './profile/profile.component';
import { NavComponent } from './nav/nav.component';

@NgModule({
  declarations: [
    AppComponent,
    HowitworksComponent,
    WatchlecturesComponent,
    UploadvidsComponent,
    ProfileComponent,
    NavComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

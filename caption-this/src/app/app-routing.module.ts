import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WatchlecturesComponent } from './watchlectures/watchlectures.component';
import { UploadvidsComponent } from './uploadvids/uploadvids.component';
import { HowitworksComponent } from './howitworks/howitworks.component';

const routes: Routes = [
  { path: 'howitworks', component: HowitworksComponent},
  { path: 'watchlectures', component: WatchlecturesComponent},
  { path: 'uploadvids', component: UploadvidsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

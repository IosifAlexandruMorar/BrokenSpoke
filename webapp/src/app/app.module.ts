import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BikeRepairListComponent } from './bike-repair-list/bike-repair-list.component';
import {BikeRepairService} from "./bike-repair.service";
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    BikeRepairListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [BikeRepairService],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { HeaderComponent } from './header/header.component';
import { SignupComponent } from './signup/signup.component';
import {AuthGuard} from "./_auth/auth.guard";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AuthInterceptor} from "./_auth/auth.interceptor";
import {UserService} from "./services/user.service";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { EmployeeComponent } from './employee/employee.component';
import { AdminComponent } from './admin/admin.component';
import { MatToolbarModule } from "@angular/material/toolbar";
import { ListEmployeesComponent } from './employee/list-employees/list-employees.component';
import { RepairsComponent } from './repairs/repairs.component';
import {MatIconModule} from "@angular/material/icon";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatButtonModule} from "@angular/material/button";
import {MatInputModule} from "@angular/material/input";
import {MatCardModule} from "@angular/material/card";
import {MatBadgeModule} from "@angular/material/badge";
import {MatListModule} from "@angular/material/list";
import { MatTableModule } from "@angular/material/table";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatGridListModule} from "@angular/material/grid-list";
import {MatSelectModule} from "@angular/material/select";
import { HomeComponent } from './home/home.component';
import {ListRepairsComponent} from "./repairs/list-repairs/list-repairs.component";
import {BikeRepairService} from "./services/bike-repair.service";
import { AddRepairComponent } from './repairs/add-repair/add-repair.component';
import {EmployeeService} from "./services/employee.service";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ForbiddenComponent,
    HeaderComponent,
    SignupComponent,
    EmployeeComponent,
    AdminComponent,
    ListEmployeesComponent,
    RepairsComponent,
    HomeComponent,
    ListRepairsComponent,
    AddRepairComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatTableModule,
    MatGridListModule,
    MatBadgeModule,
    MatAutocompleteModule,
    MatListModule,
    MatSelectModule
  ],
  providers: [
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass:AuthInterceptor,
      multi:true
    },
    UserService,
    BikeRepairService,
    EmployeeService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

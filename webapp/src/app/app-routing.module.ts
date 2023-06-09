import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {SignupComponent} from "./signup/signup.component";
import {ForbiddenComponent} from "./forbidden/forbidden.component";
import {EmployeeComponent} from "./employee/employee.component";
import {AuthGuard} from "./_auth/auth.guard";
import {AdminComponent} from "./admin/admin.component";
import {ListEmployeesComponent} from "./employee/list-employees/list-employees.component";
import {ListRepairsComponent} from "./repairs/list-repairs/list-repairs.component";
import {HomeComponent} from "./home/home.component";
import {AddRepairComponent} from "./repairs/add-repair/add-repair.component";
import {ListRepairsEmployeeComponent} from "./repairs/list-repairs-employee/list-repairs-employee.component";
import {UpdatePasswordComponent} from "./update-password/update-password.component";


const routes: Routes = [
  {path: '',component:HomeComponent},
  {path: 'login',component:LoginComponent},
  {path: 'signup',component:SignupComponent},
  {path:'forbidden', component: ForbiddenComponent},
  {path: 'employee',component:EmployeeComponent, canActivate:[AuthGuard],data:{role:['Employee']}},
  {path: 'admin',component:AdminComponent, canActivate:[AuthGuard],data:{role:['Admin']} },
  {path: 'employees/list',component:ListEmployeesComponent, data:{role:['Admin']} },
  {path: 'employee/repairs',component:ListRepairsComponent, data:{role:['Employee']} },
  {path: 'employee/my-repairs',component:ListRepairsEmployeeComponent, data:{role:['Employee']} },
  {path: 'bikerepair/add', component: AddRepairComponent,data:{role:['Employee']}},
  {path: 'employee/changePassword',component: UpdatePasswordComponent,data:{role:['Employee']}}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

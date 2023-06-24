import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {UserService} from "../services/user.service";
import {UserAuthService} from "../services/user-auth.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  userName:string = "";
  employeeId:number | undefined;
  message: string | undefined;
  constructor(private router:Router, private userService:UserService, private userAuthService:UserAuthService) { }


  ngOnInit(): void {
    this.accessUser();
  }

  public isEmployee(){
    console.log(this.userAuthService.isEmployee());
    return this.userAuthService.isEmployee();

  }

  accessUser(){
    this.userService.accessEmployeeOnly().subscribe(
      (response) =>{
        console.log(response);
        this.message = response;
        this.getUserLoggedIn();
      },
      (error)=>{
        console.log(error);
      }
    )
  }

  getUserLoggedIn(){
    this.userService.getLoggedInUser().subscribe(resp =>{
      this.userName = resp.firstName;
      this.employeeId = resp.employeeId;
    },(error:HttpErrorResponse) =>{
      console.log(error);
    });
  }

}

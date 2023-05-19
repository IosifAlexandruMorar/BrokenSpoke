import { Component, OnInit } from '@angular/core';
import {UserAuthService} from "../services/user-auth.service";
import {Router} from "@angular/router";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private userAuthService: UserAuthService, private router:Router, private userService:UserService) { }

  ngOnInit(): void {
  }

  public isLoggedIn(){
    return this.userAuthService.isLoggedIn();
  }

  public logout(){
    this.userAuthService.clear();
    this.router.navigate(['/']);
  }

  public roleMatch(allowedRoles: any):boolean{
    return this.userService.roleMatch(allowedRoles);
  }

  public isEmployee(){
    return this.userAuthService.isEmployee();
  }

  public isAdmin(){
    return this.userAuthService.isAdmin();
  }
}

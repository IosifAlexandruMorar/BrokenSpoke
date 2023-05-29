import { Component, OnInit } from '@angular/core';
import {UserService} from "../services/user.service";
import {Router} from "@angular/router";
import {UserAuthService} from "../services/user-auth.service";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  isLoggedIn = false;
  constructor(private userService: UserService, private router:Router, private userAuthService: UserAuthService) { }

  ngOnInit(): void {
  }

  hide = true;
  output: any;

  login(loginForm: NgForm) {

    this.userService.logInUser(loginForm.value).subscribe(
      (response:any)=> {
        console.log(response.user.role.roleName);
        console.log(response.jwtToken);
        this.userAuthService.setRole(response.user.role.roleName);
        this.userAuthService.setToken(response.jwtToken);

        const role = response.user.role.roleName;
        if (role === 'Employee') {
          console.log("Employee here");
          this.router.navigate(['/']);
        } else {
          console.log("Admin here");
          this.router.navigate(['/']);
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }
}

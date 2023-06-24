import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {UserService} from "../services/user.service";
import {UserAuthService} from "../services/user-auth.service";
import {catchError, map, Observable, throwError} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.css']
})
export class UpdatePasswordComponent {
  oldPassword: string = ''
  newPassword: string = ''
  userName: string = ''
  employeeId: number | undefined;
  showNotification: boolean = false

  constructor(private http: HttpClient, private userService: UserService, private userAuthService: UserAuthService, private route: Router) {
  }

  getUserLoggedIn(): Observable<any> {
    return this.userService.getLoggedInUser().pipe(
      map(resp => {
        this.userName = resp.firstName;
        this.employeeId = resp.employeeId;
        return resp.employeeId; // Returnare loginId
      }),
      catchError((error: HttpErrorResponse) => {
        console.log(error);
        return throwError(error);
      })
    );
  }

  updatePassword() {
    this.getUserLoggedIn().subscribe(
      loginId => {
        this.userService.updatePassword(loginId, this.oldPassword, this.newPassword).subscribe(
          response => {
            console.log('Password updated successfully');
            this.showNotification = true;
          });
      })
  }

  closeNotification() {
    this.showNotification = false;
  }
}

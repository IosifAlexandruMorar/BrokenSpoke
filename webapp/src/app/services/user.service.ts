import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserAuthService} from "./user-auth.service";
import {SignupDto} from "../_model/signupDto";
import {LogInDto} from "../_model/logInDto";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userUrl = "http://localhost:8083";

  //for login and register, so the backend knows that doesn't have to find "Authorization" in headers in JwtRequestFilter class
  requestHeader = new HttpHeaders(
    { "No-Auth":"True"}
  );
  constructor(private httpClient:HttpClient, private userAuthService:UserAuthService) { }

  signUpUser(formData: SignupDto) {
    return this.httpClient.post<SignupDto>(`${this.userUrl}/api/v1/employee`, formData);
  }

  public logInUser(logInUser: LogInDto){
    return this.httpClient.post(this.userUrl+"/authenticate",logInUser, {headers:this.requestHeader});
  }

  // @ts-ignore
  public roleMatch(allowedRoles) :boolean{
    let isMatch = false;
    const userRole: any = this.userAuthService.getRole();
    if(userRole != null && userRole){
      for(let i = 0; i < userRole.length; i++){
        for(let j = 0; j < allowedRoles.length; j++){
          if(userRole.roleName === allowedRoles[j]){
            isMatch = true;
            return isMatch;
          }
          else{
            return isMatch;
          }
        }
      }
    }
  }

  public accessEmployeeOnly(){
    return this.httpClient.get(this.userUrl + '/api/v1/employee', {responseType:"text"});
  }

  public accessAdminOnly(){
    return this.httpClient.get(this.userUrl + '/api/v1/admin', {responseType:"text"});
  }

  public getLoggedInUser(){
    return this.httpClient.get<SignupDto>(this.userUrl + '/api/v1/userLoggedIn');
  }

  public getAllEmployees(){
    return this.httpClient.get<Array<SignupDto>>(this.userUrl + "/employees/list");
  }


}

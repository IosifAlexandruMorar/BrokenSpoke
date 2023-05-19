import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  constructor() { }

  public setRole(role:any){
    localStorage.setItem("role",JSON.stringify(role));
  }

  public getRole(): any {
    // @ts-ignore
    return JSON.parse(localStorage.getItem('role'));
  }

  public setToken(jwtToken: string) {
    localStorage.setItem('jwtToken', jwtToken);
  }

  public getToken(): string {
    // @ts-ignore
    return localStorage.getItem('jwtToken');
  }

  public clear() {
    localStorage.clear();
  }

  public isLoggedIn() {
    return this.getRole() && this.getToken();
  }

  public isEmployee(){
    const role:any = this.getRole();
    return role === 'Employee';
  }

  public isAdmin(){
    const role:any = this.getRole();
    return role === 'Admin';
  }
}

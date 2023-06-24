import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserAuthService} from "./user-auth.service";
import {forkJoin, map, Observable, of, switchMap} from "rxjs";
import {EmployeeModel} from "../_model/employee.model";
import {LogInDto} from "../_model/logInDto";

import {BikeRepair} from "../_model/bike-repair.model";

@Injectable()
export class EmployeeService {
  private employeesURL = 'http://localhost:8083/api/v1/employees';
  private loginsURL = 'http://localhost:8083/api/v1/login';

  constructor(private httpClient: HttpClient, private userAuthService: UserAuthService) {
  }

  getEmployees(): Observable<EmployeeModel[]> {
    const token = this.userAuthService.getToken();
    console.log(token);
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.httpClient.get<Array<EmployeeModel>>(this.employeesURL, { headers });
  }

getLogins():  Observable<LogInDto[]> {
  const token = this.userAuthService.getToken();
  console.log(token);
  const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
  return this.httpClient.get<Array<LogInDto>>(this.loginsURL, { headers });
}
  updateLogin(loginDto: LogInDto):  Observable<LogInDto[]> {
    const token = this.userAuthService.getToken();
    console.log(token);
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.httpClient.put<Array<LogInDto>>(`${this.loginsURL}/set_status/id=${loginDto.loginId}`, loginDto.approved, { headers });
  }

}

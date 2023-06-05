import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserAuthService} from "./user-auth.service";
import {Observable} from "rxjs";
import {EmployeeModel} from "../_model/employee.model";

@Injectable()
export class EmployeeService {
  private employeesURL = 'http://localhost:8083/api/v1/employees';


  constructor(private httpClient: HttpClient, private userAuthService: UserAuthService) {
  }

  getEmployees(): Observable<EmployeeModel[]> {
    const token = this.userAuthService.getToken();
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.httpClient.get<Array<EmployeeModel>>(this.employeesURL, { headers });
  }
}

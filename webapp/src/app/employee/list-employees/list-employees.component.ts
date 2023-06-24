import { Component, OnInit } from '@angular/core';
import {EmployeeService} from "../../services/employee.service";
import {LogInDto} from "../../_model/logInDto";


@Component({
  selector: 'app-list-employees',
  templateUrl: './list-employees.component.html',
  styleUrls: ['./list-employees.component.css']
})
export class ListEmployeesComponent implements OnInit {
  logins: LogInDto[] = [];

  constructor(private employeeService: EmployeeService) { }

  ngOnInit() {
    this.getLogins().subscribe((logins: LogInDto[]) => {
      this.logins = logins;
      console.log(logins)
    });
  }

  getLogins() {
    return this.employeeService.getLogins()
  }

  toggleApproved(login: LogInDto) {
    login.approved = !login.approved;
    this.employeeService.updateLogin(login).subscribe();
  }

}

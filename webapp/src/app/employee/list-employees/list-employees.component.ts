import { Component, OnInit } from '@angular/core';
import {EmployeeModel} from "../../_model/employee.model";
import {EmployeeService} from "../../services/employee.service";
import {UserService} from "../../services/user.service";
import {SignupDto} from "../../_model/signupDto";

@Component({
  selector: 'app-list-employees',
  templateUrl: './list-employees.component.html',
  styleUrls: ['./list-employees.component.css']
})
export class ListEmployeesComponent implements OnInit {
  employeesToApprove: SignupDto[] = [];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getEmployees();
  }

  getEmployees() {
    this.userService.getAllEmployees().subscribe(
      (response) => {
        this.employeesToApprove = response;
      },
      (error) => {
        console.error('Error fetching employees:', error);
      }
    );
  }

}

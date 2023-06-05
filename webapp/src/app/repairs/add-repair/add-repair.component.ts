import { Component, OnInit } from '@angular/core';
import {BikeRepair} from "../../_model/bike-repair.model";
import {BikeRepairService} from "../../services/bike-repair.service";
import {Router} from "@angular/router";
import {EmployeeModel} from "../../_model/employee.model";
import {EmployeeService} from "../../services/employee.service";

@Component({
  selector: 'app-add-repair',
  templateUrl: './add-repair.component.html',
  styleUrls: ['./add-repair.component.css']
})
export class AddRepairComponent {
  bikeRepair = {
    // id: 0,
    description: '',
    startDate: '',
    dueDate: '',
    clientName: '',
    clientPhone: 0,
    status: '',
    price: 0,
    employeeId: 0,
    employee: {
      employeeId: 0,
      firstName: '',
      lastName: '',
      hireDate: ''
    }} as BikeRepair;

  employees: EmployeeModel[] = [];
  constructor(private bikeRepairService: BikeRepairService, private employeeService: EmployeeService,
              private route: Router) { }

  ngOnInit(): void {
    this.employeeService.getEmployees()
      .subscribe((employees: EmployeeModel[]) => this.employees = employees);
  }

  addBikeRepair() {
    console.log(this.bikeRepair)

    this.bikeRepairService
      .addBikeRepair(this.bikeRepair)
      .subscribe((data: BikeRepair) => {
        alert("New item added successfully!");
        this.route.navigate(['/bikerepair']);
      })
  }

  cancel(): void {
    this.route.navigate(['/bikerepair']);
  }
}

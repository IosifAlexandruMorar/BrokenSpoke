import { Component, OnInit } from '@angular/core';
import {BikeRepair} from "../../_model/bike-repair.model";
import {EmployeeModel} from "../../_model/employee.model";
import {BikeRepairService} from "../../services/bike-repair.service";
import {UserAuthService} from "../../services/user-auth.service";
import {Router} from "@angular/router";
import {EmployeeService} from "../../services/employee.service";

@Component({
  selector: 'app-list-repairs-employee',
  templateUrl: './list-repairs-employee.component.html',
  styleUrls: ['./list-repairs-employee.component.css']
})
export class ListRepairsEmployeeComponent implements OnInit {

  repairs: BikeRepair[] = [];
  repairsItem: BikeRepair = {
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
    }
  };

  employee: EmployeeModel = {} as EmployeeModel;
  employees: EmployeeModel[] = [];
  markedForDeletion: BikeRepair| undefined;
  markedForUpdate: BikeRepair | undefined;
  selectedEmployeeId: number=0;
  constructor(private bikeService: BikeRepairService, private userAuthService: UserAuthService, private router: Router, private employeeService: EmployeeService) { }

  selectEmployee(employeeId: number) {
    this.selectedEmployeeId = employeeId;
  }
  // public isLoggedIn(){
  //   return this.userAuthService.isLoggedIn();
  // }
  ngOnInit(): void {
    this.loadRepairs()
    this.employeeService.getEmployees()
      .subscribe((employees: EmployeeModel[]) => this.employees = employees);
  }

  loadRepairs():void{
    this.bikeService.getBikeRepairsByEmployee()
      .subscribe((repairs: BikeRepair[]) => {
        this.repairs = repairs;
      });
  }
  startUpdate(bikeRepair: BikeRepair): void {
    this.markedForUpdate = bikeRepair;
    this.repairsItem = { ...bikeRepair,employeeId: this.selectedEmployeeId  };
    console.log('new employee', this.repairsItem)

  }

  confirmUpdate():void{
    if (this.repairsItem) {
      console.log(this.repairsItem.employeeId)
      this.bikeService
        .updateBikeRepair(this.repairsItem)

        .subscribe((data: BikeRepair) => {
          alert('Repair updated successfully!');
          console.log(this.repairsItem)
          this.markedForUpdate = undefined;
          this.loadRepairs();
        });
    }
  }
  cancelUpdate(): void {
    this.markedForUpdate = undefined;
  }

  startDelete(bikeRepair: BikeRepair): void {
    this.markedForDeletion = bikeRepair;
  }
  confirmDelete():void{
    if (this.markedForDeletion) {
      this.bikeService.
      deleteBikeRepair(this.markedForDeletion.repairId!)
        .subscribe((data) => {
          alert('Repair deleted successfully!');
          this.markedForDeletion = undefined;
          this.loadRepairs();

        });
    }
  }

  cancelDelete(): void {
    this.markedForDeletion = undefined;
  }
}

import { Component, OnInit } from '@angular/core';
import {BikeRepair} from "../../_model/bike-repair.model";
import {BikeRepairService} from "../../services/bike-repair.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-repair',
  templateUrl: './add-repair.component.html',
  styleUrls: ['./add-repair.component.css']
})
export class AddRepairComponent {
  bikeRepair = {
    id: 0,
    description: '',
    startDate: '',
    dueDate: '',
    clientName: '',
    clientPhone: 0,
    status: '',
    price: 0, } as BikeRepair;

  constructor(private bikeRepairService: BikeRepairService,
              private route: Router) { }

  ngOnInit(): void {
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

import { Component, OnInit } from '@angular/core';
import {BikeRepairService} from "../bike-repair.service";
import {BikeRepair} from "../bike-repair.model";

@Component({
  selector: 'app-bike-repair-list',
  templateUrl: './bike-repair-list.component.html',
  styleUrls: ['./bike-repair-list.component.css']
})
export class BikeRepairListComponent implements OnInit {
  repairs: BikeRepair[] = [];

  constructor(private bikeService: BikeRepairService) { }

  ngOnInit(): void {
    this.bikeService.getBikeRepair()
      .subscribe((repairs: BikeRepair[]) => this.repairs = repairs);
  }

}

import { Component, OnInit } from '@angular/core';
import {BikeRepair} from "../../_model/bike-repair.model";
import {BikeRepairService} from "../../services/bike-repair.service";
import {UserAuthService} from "../../services/user-auth.service";

@Component({
  selector: 'app-list-repairs',
  templateUrl: './list-repairs.component.html',
  styleUrls: ['./list-repairs.component.css']
})
export class ListRepairsComponent implements OnInit {
  repairs: BikeRepair[] = [];

  constructor(private bikeService: BikeRepairService, private userAuthService: UserAuthService) { }

  // public isLoggedIn(){
  //   return this.userAuthService.isLoggedIn();
  // }
  ngOnInit(): void {
    this.bikeService.getBikeRepair()
      .subscribe((repairs: BikeRepair[]) => this.repairs = repairs);
  }

}

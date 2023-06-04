import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {BikeRepair} from "../_model/bike-repair.model";

@Injectable()
export class BikeRepairService {
  private bikeRepairget = 'http://localhost:8083/api/v1/bikerepair';
  private bikeRepairURL = 'http://localhost:8083/api/v1';

  constructor(private httpClient: HttpClient) {
  }

  getBikeRepair(): Observable<BikeRepair[]> {
    return this.httpClient.get<Array<BikeRepair>>(this.bikeRepairget);
  }

  addBikeRepair(bikeRepair: BikeRepair): Observable<BikeRepair> {
    console.log(bikeRepair)
    return this.httpClient.post<BikeRepair>(`${this.bikeRepairURL}/bikerepair`, bikeRepair )
  }
}

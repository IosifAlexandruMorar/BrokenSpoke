import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {BikeRepair} from "../_model/bike-repair.model";
import {UserAuthService} from "./user-auth.service";

@Injectable()
export class BikeRepairService {
  private bikeRepairEmployeeGet = 'http://localhost:8083/api/v1/bikerepair/user';
  private bikeRepairget = 'http://localhost:8083/api/v1/bikerepair';
  private bikeRepairURL = 'http://localhost:8083/api/v1';

  constructor(private httpClient: HttpClient, private userAuthService: UserAuthService) {
  }

  getBikeRepair(): Observable<BikeRepair[]> {
    return this.httpClient.get<Array<BikeRepair>>(this.bikeRepairget);
  }
  getBikeRepairsByEmployee(): Observable<BikeRepair[]> {
    return this.httpClient.get<Array<BikeRepair>>(this.bikeRepairEmployeeGet);
  }


  addBikeRepair(bikeRepair: BikeRepair): Observable<BikeRepair> {
    console.log(bikeRepair)
    const token = this.userAuthService.getToken();
    console.log(token);
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.httpClient.post<BikeRepair>(`${this.bikeRepairURL}/bikerepair`, bikeRepair, { headers } )
  }

  updateBikeRepair(bikeRepair: BikeRepair): Observable<BikeRepair> {
    const token = this.userAuthService.getToken();
    console.log(token);
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.httpClient.put<BikeRepair>(`${this.bikeRepairURL}/bikerepair/id=${bikeRepair.repairId}`, bikeRepair, { headers } )
  }

  deleteBikeRepair(id: number): Observable<BikeRepair> {
    const token = this.userAuthService.getToken();
    console.log(token);
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.httpClient.delete<BikeRepair>(`${this.bikeRepairURL}/bikerepair/id=${id}`);
  }


}

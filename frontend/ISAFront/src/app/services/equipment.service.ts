import { Injectable } from '@angular/core';
import { Equipment } from '../model/equipment';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { PurchaseEquipmentDto } from '../dto/PurchaseEquipmentDto';

@Injectable({
  providedIn: 'root'
})
export class EquipmentService {
  url = "http://localhost:8080";

  constructor(private http: HttpClient) { }

  getByCompanyId(id:number) : Observable<Equipment[]>{
    return this.http.get<Equipment[]>(this.url + `/api/equipment/byCompanyID/${id}`);
  }

  getAll() :  Observable<Equipment[]>{
    return this.http.get<Equipment[]>(this.url + `/api/equipment/all`);
  }
  
  getById(id: number) : Observable<Equipment>{
    return this.http.get<Equipment>(this.url + `/api/equipment/byID/${id}`);
  }

  purchase(p : PurchaseEquipmentDto){
    return this.http.post<boolean>(this.url + '/api/equipment/purchase', p);
  }
}

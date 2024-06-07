import { Injectable } from '@angular/core';
import { PredefinedAppointmentDto } from '../dto/PredefinedAppointmentDto';
import { HttpClient } from '@angular/common/http';
import { Appointment } from '../model/appointment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  url = "http://localhost:8080";
  constructor(private http: HttpClient) { }

  addPredefinedAppointment(s: PredefinedAppointmentDto){
    return this.http.post<Boolean>(this.url + '/api/appointments/addPredefinedAppointment', s);
  }

  getAll(){
    return this.http.get<Appointment[]>(this.url + '/api/appointments/all');
  }

  getByCompanyId(id:number) : Observable<Appointment[]>{
    return this.http.get<Appointment[]>(this.url + `/api/appointments/byCompanyID/${id}`);
  }

}

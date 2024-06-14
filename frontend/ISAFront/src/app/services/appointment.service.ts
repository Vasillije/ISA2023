import { Injectable } from '@angular/core';
import { PredefinedAppointmentDto } from '../dto/PredefinedAppointmentDto';
import { HttpClient } from '@angular/common/http';
import { Appointment } from '../model/appointment';
import { Observable } from 'rxjs';
import { CancelAppointmentDto } from '../dto/CancelAppointmentDto';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  url = "http://localhost:8080";
  constructor(private http: HttpClient) { }

  addPredefinedAppointment(s: PredefinedAppointmentDto){
    console.log(s)
    return this.http.post<Boolean>(this.url + '/api/appointments/addPredefinedAppointment', s);
  }

  getAll(){
    return this.http.get<Appointment[]>(this.url + '/api/appointments/all');
  }

  getValidByCompanyId(id:number) : Observable<Appointment[]>{
    return this.http.get<Appointment[]>(this.url + `/api/appointments/validByCompanyID/${id}`);
  }

  getValidScheduledByUserId(id : number) : Observable<Appointment[]>{
    return this.http.get<Appointment[]>(this.url + `/api/appointments/validScheduledByUserID/${id}`);
  }

  cancelAppointment(c : CancelAppointmentDto){
    return this.http.post<Appointment[]>(this.url + '/api/appointments/cancelAppointment', c);
  }

}

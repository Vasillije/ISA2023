import { Injectable } from '@angular/core';
import { PredefinedAppointmentDto } from '../dto/PredefinedAppointmentDto';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  url = "http://localhost:8080";
  constructor(private http: HttpClient) { }

  
  addPredefinedAppointment(s: PredefinedAppointmentDto){
    return this.http.post<Boolean>(this.url + '/api/appointments/addPredefinedAppointment', s);
  }
}

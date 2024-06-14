import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Company } from '../model/company';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class ComplaintService {

  url = "http://localhost:8080";
  constructor(private http: HttpClient) { }

  getUsersCompanies(id : number){
    return this.http.get<Company[]>(this.url + `/api/complaints/usersCompanies/${id}`);
  }

  //getCompanyAdminsFullName(id : number){
    //return this.http.get<User[]>(this.url + `/api/complaints/CompanyAdmin/${id}`);
  //}

  getAppointmentsAdmin(id : number){
    return this.http.get<User[]>(this.url + `/api/complaints/appointmentsAdmin/${id}`);
  }
  

}

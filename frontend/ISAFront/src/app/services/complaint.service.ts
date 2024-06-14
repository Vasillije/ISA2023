import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Company } from '../model/company';
import { User } from '../model/user';
import { Complaint } from '../model/complaint';

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
  addComplaint (complaint : Complaint){
    return this.http.post<Complaint>(this.url + `/api/complaints/addcomplaint`,complaint);
  }

  getAppointmentsAdmin(id : number){
    return this.http.get<User[]>(this.url + `/api/complaints/appointmentsAdmin/${id}`);
  }
  

}

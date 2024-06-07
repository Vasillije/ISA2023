import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Company } from '../model/company';
import { Appointment } from '../model/appointment';
import { SearchDto } from '../dto/searchDto';
import { CompanyDto } from '../dto/CompanyDto';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  url = "http://localhost:8080";
  constructor(private http: HttpClient) { }

  getAll(){
    return this.http.get<Company[]>(this.url + '/api/company/all');
  }

  search(s: SearchDto){
    return this.http.post<Company[]>(this.url + '/api/company/search', s);
  }
}

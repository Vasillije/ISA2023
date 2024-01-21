import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Company } from '../model/company';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  url = "http://localhost:8080";
  constructor(private http: HttpClient) { }

  getAll(){
    return this.http.get<Company[]>(this.url + '/api/company/all');
  }
}

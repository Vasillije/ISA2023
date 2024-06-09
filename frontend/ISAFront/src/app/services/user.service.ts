import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  
  url = "http://localhost:8080";
  constructor(private http: HttpClient) { }


}

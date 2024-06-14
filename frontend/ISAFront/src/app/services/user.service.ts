import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/user';
import { UpdateUserDto } from '../dto/UpdateUserDto';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  
  url = "http://localhost:8080";
  constructor(private http: HttpClient) { }

  getUserById(id : number){
    return this.http.get<User>(this.url + `/api/users/getUserById/${id}`);
  }

  update(u : UpdateUserDto){
    return this.http.post<User>(this.url + '/api/users/update', u);
  }

}

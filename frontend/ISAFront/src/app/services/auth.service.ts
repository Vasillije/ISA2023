import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserRequestDto } from '../dto/UserRequestDto';
import { User } from '../model/user';
import { JwtAuthenticationRequestDto } from '../dto/JwtAuthenticationRequestDto';
import { UserTokenStateDto } from '../dto/UserTokenStateDto';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  url = "http://localhost:8080";
  constructor(private http: HttpClient,private router: Router) { }


  private access_token = null;
  public show: number = 0;

  register(u: UserRequestDto){
    return this.http.post<User>(this.url + '/api/auth/register', u);
  }


  login(u: JwtAuthenticationRequestDto){
    console.log(u);
    return this.http.post<UserTokenStateDto>(this.url + '/api/auth/login', u);
  }

  logout(){
    localStorage.removeItem("JWT");
    this.userLoginTracker();
    this.router.navigate(['/login']);
  }
  
  tokenIsPresent() {
    return this.access_token != undefined && this.access_token != null;
  }

  getToken() {
    return this.access_token;
  }

  userLoginTracker(){
   let logged= localStorage.getItem('JWT');
   if (logged){
      this.show =1;
   }
   else {
    this.show = 0;
   }
   console.log('ovo je showww',this.show);
  }


}

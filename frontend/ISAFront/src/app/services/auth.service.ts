import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserRequestDto } from '../dto/UserRequestDto';
import { User } from '../model/user';
import { JwtAuthenticationRequestDto } from '../dto/JwtAuthenticationRequestDto';
import { UserTokenStateDto } from '../dto/UserTokenStateDto';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';

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

  getTokenInfo() : any{
    const jwt =localStorage.getItem('JWT');
    if(jwt != null){
      const tokenInfo = jwtDecode(jwt);
      console.log(tokenInfo)
      return tokenInfo;
    }
  }

  getUserId() : number | null {
    const tokenInfo = this.getTokenInfo();
    if (tokenInfo) {
      console.log(tokenInfo.userId)
      return tokenInfo.userId;
    }
    return null;
  }

  getRole() : string | null {
    const tokenInfo = this.getTokenInfo();
    if (tokenInfo) {
      console.log(tokenInfo.role)
      return tokenInfo.role[0].name;

    }
    return null;
  }
  

  getSubject() : string | null {
    const tokenInfo = this.getTokenInfo();
    if (tokenInfo) {
      return tokenInfo.sub;
    }
    return null;
  }

  getCompanyId() : string | null {
    const tokenInfo = this.getTokenInfo();
    if (tokenInfo) {
      return tokenInfo.companyId;
    }
    return null;
  }

  getToken() {
    return this.access_token;
  }

  userLoginTracker() : Boolean{
   let logged= localStorage.getItem('JWT');
   if (logged){
      this.show =1;
      return true;
   }
   else {
    this.show = 0;
    return false;
   }
  }


}

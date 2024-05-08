import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { JwtAuthenticationRequestDto } from '../../dto/JwtAuthenticationRequestDto';
import { UserTokenStateDto } from '../../dto/UserTokenStateDto';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { Subject, takeUntil } from 'rxjs';
import { UserService } from '../../services/user.service';
import { HttpErrorResponse } from '@angular/common/http';

interface DisplayMessage {
  msgType: string;
  msgBody: string;
}

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css'
})

export class LoginPageComponent {

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private router: Router,
  ) { }


  private jwtRequest: JwtAuthenticationRequestDto = {
    email: '',
    password: ''
  };

  login(credentials: { email: string, password: string }) {
    this.jwtRequest.email= credentials.email;
    this.jwtRequest.password=credentials.password;
    
    console.log(this.jwtRequest);

    this.authService.login(this.jwtRequest).subscribe(
      (data) => {
        alert('Korisnik uspešno prijavljen.');
        localStorage.setItem('JWT', data.accessToken);
        this.authService.userLoginTracker();
        this.router.navigate(['']);
      },
      (error: HttpErrorResponse) => {
        alert('Korisik sa ovim mejlom i lozinkom ne postoji');
      }
    ); 
  }
}



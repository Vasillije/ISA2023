import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ISAFront';
  constructor(private router: Router, public authService : AuthService) {}
  
  redirectToRegister(){
    this.router.navigate(['/register']);
  }
  redirectToAppointmentForm(){
    this.router.navigate(['/appointment-form']);
  }

  redirectToLogin(){
    this.router.navigate(['/login']);
  }

  logout(){
    this.authService.logout();
  }
}

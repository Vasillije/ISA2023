import { Component } from '@angular/core';
import { UserRequestDto } from '../../dto/UserRequestDto';
import { AuthService } from '../../services/auth.service';
import { User } from '../../model/user';
import { Router } from '@angular/router';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  constructor(private authService : AuthService, private router: Router){}
  user!: User;


  register(result : any){

    const userDto : UserRequestDto = {
      password: result.password,
      name: result.name,
      surname: result.surname,
      email: result.email,
      city: result.city,
      country: result.country,
      phoneNumber: result.phoneNumber,
      profession: result.profession,
      companyInfo: result.companyInfo
    }

    if(result.password != result.retypedpassword){
      alert("Passwords dont match");
      return;
    }

    if (result.email == '' || result.password == '' || result.name == '' || result.surname == '' || result.city == '' || result.country == '' 
      || result.phoneNumber == '' || result.profession == '' || result.companyInfo == '') {
      alert('You must fill in all fields.');
      return; 
    }

    console.log(userDto);
    alert('Confirmation email is sent')
    this.authService.register(userDto).subscribe((data)=>{
      this.user=data;
      alert('User succlesfully registered');
      this.router.navigate(['/login']);
    },
    (error) => {
      if (error.status === 409) {
        alert('User with this email already exists');
      } else {
        alert('An error occurred. Please try again later.');
      }
    }
  );
  }
}

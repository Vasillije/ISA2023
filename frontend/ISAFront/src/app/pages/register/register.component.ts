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

  userDto : UserRequestDto = {
    password: '',
    id: 0,
    name: '',
    surname: '',
    email: ''
  }
  constructor(private authService : AuthService, private router: Router){}
  user!: User;


  register(result : any){
    var emailElement = document.getElementById("email") as HTMLInputElement;
    var email : string = emailElement ? emailElement.value : '';
   
    var passwordElement = document.getElementById("password") as HTMLInputElement;
    var password : string = passwordElement ? passwordElement.value : ''; 

    var retypedPasswordElement = document.getElementById("retypedpassword") as HTMLInputElement;
    var retypedPassword : string = retypedPasswordElement ? retypedPasswordElement.value : ''; 

    var nameElement = document.getElementById("name") as HTMLInputElement;
    var name : string = nameElement ? nameElement.value : '';

    var surnameElement = document.getElementById("surname") as HTMLInputElement;
    var surname : string = surnameElement ? surnameElement.value : '';

    if(password != retypedPassword){
      alert("Passwords doesnt match");
      return;
    }

    if (email == '' || password == '' || name == '' || surname == '') {
      alert('You must fill in all fields.');
      return; 
    }

    this.userDto.email = email;
    this.userDto.password = password;
    this.userDto.name = name;
    this.userDto.surname = surname;
    
    this.authService.register(this.userDto).subscribe((data)=>{
      this.user=data;
      alert('User succlesfully registered');
      this.router.navigate(['/login']);
    });
  }
}

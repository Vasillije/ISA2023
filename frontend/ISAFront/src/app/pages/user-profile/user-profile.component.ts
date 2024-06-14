import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { User } from '../../model/user';
import { Appointment } from '../../model/appointment';
import { UpdateUserDto } from '../../dto/UpdateUserDto';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent {
  constructor(private route: ActivatedRoute, private router: Router, private userService : UserService) { }

  userId! : number;
  user! : User;
  penaltyPoints! : number;
  appointments : Appointment[] = [];

  ngOnInit(): void {
    this.route.paramMap.subscribe(data => {
      const id = +data.get('id')!;
      this.userId = id;
      this.getUserById(id);
    });
  }

  getUserById(id : number){
    this.userService.getUserById(id).subscribe(
      (data) => {
        this.user = data
        this.penaltyPoints = this.user.penaltyPoints;
        this.appointments = this.user.appointments;
      }
    );
  }

  update(result : any){
    const u : UpdateUserDto = {
      id: this.userId,
      password: result.password,
      name: result.name,
      surname: result.surname,
      town: result.town,
      country: result.country,
      phoneNumber: result.phoneNumber,
      profession: result.profession,
      companyInfo: result.companyInfo,
    }

    this.userService.update(u).subscribe(
      (data) => this.user = data,
      (error) => {
        console.error('There was an error!', error);
      }
    );
  }

}

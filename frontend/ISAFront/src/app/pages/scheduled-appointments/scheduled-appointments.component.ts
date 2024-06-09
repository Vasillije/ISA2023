import { Component, OnInit } from '@angular/core';
import { Appointment } from '../../model/appointment';
import { AppointmentService } from '../../services/appointment.service';
import { User } from '../../model/user';
import { Router } from '@angular/router';
import { CancelAppointmentDto } from '../../dto/CancelAppointmentDto';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-scheduled-appointments',
  templateUrl: './scheduled-appointments.component.html',
  styleUrl: './scheduled-appointments.component.css'
})
export class ScheduledAppointmentsComponent {
  appointments : Appointment[] = [];
  user! : User;
  userId! : number;
  appointment! : Appointment;
  
  constructor(private appointmentService : AppointmentService, private router: Router, private route: ActivatedRoute){}

  ngOnInit(): void {
    this.route.paramMap.subscribe(data => {
      const id = +data.get('id')!;
      this.userId = id;
    });

    this.appointmentService.getValidScheduledByUserId(this.userId).subscribe(
      (data) => {
        this.appointments = data;
      },
      (error) => {
        console.error('There was an error!', error);
      }
    );
    
  }

  cancelAppointment(id : number){
    const currentDateTime = new Date();

    const c : CancelAppointmentDto = {
      userId: this.userId,
      appointmentId: id,
      penaltyPoints: 0,
      cancelDateAndTime: currentDateTime
    };

    this.appointmentService.cancelAppointment(c).subscribe(
      (data) => {
        this.appointments = data;
      }
    );
    }
   
  
}

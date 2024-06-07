import { Component } from '@angular/core';
import { Appointment } from '../../model/appointment';
import { AppointmentService } from '../../services/appointment.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-show-appointments',
  templateUrl: './show-appointments.component.html',
  styleUrl: './show-appointments.component.css'
})
export class ShowAppointmentsComponent {

  appointments : Appointment[] = [];
  companyId!: string;
  companyIdNumber! : number;

  constructor(private route: ActivatedRoute, private appointmentService : AppointmentService, private router: Router){}

  ngOnInit(): void {
    this.route.paramMap.subscribe(data => {
      const id = +data.get('id')!;
      this.companyIdNumber = id;
      this.getAppointmentsByCompanyID(id);
    });
  }

  getAppointmentsByCompanyID(id: number): void {
    this.appointmentService.getByCompanyId(id).subscribe(
      (data) => {
        this.appointments = data;
      },
      (error) => {
        console.error('There was an error!', error);
      }
    );
  }

  showEquipment(){
    this.router.navigate(['show-equipment/' + this.companyIdNumber]);
  }

}

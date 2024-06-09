import { Component } from '@angular/core';
import { provideNativeDateAdapter } from '@angular/material/core';
import { PredefinedAppointmentDto } from '../../dto/PredefinedAppointmentDto';
import { AppointmentService } from '../../services/appointment.service';


@Component({
  selector: 'app-appointment-form',
  templateUrl: './appointment-form.component.html',
  styleUrl: './appointment-form.component.css',
  providers: [provideNativeDateAdapter()],
})


export class AppointmentFormComponent {

  constructor(private appointmentService : AppointmentService){}

  //moram ovde da dodam companyAdminFullName

  isSuccessful! : Boolean; 
  selectedHour: string = '';
  selectedHour2: string = '';
  selectedMinutes: string = '';
  selectedMinutes2: string = '';
  selectedDate!: Date;
  minDate = new Date();

  datumIzabran(date: any) {
    this.selectedDate = date.value;
  }

  selectHour(hour: string) {
    this.selectedHour = hour;
    console.log(this.selectedHour);
  }


  selectHour2(hour: string) {
    this.selectedHour2 = hour;
    console.log(this.selectedHour2);
  }


  selectMinutes(minutes: string) {
    this.selectedMinutes = minutes;
    console.log(this.selectedMinutes);
  }


  selectMinutes2(minutes: string) {
    this.selectedMinutes2 = minutes;
    console.log(this.selectedMinutes2);
  }

  calculateDuration(startHour: number, startMins: number, finishHour: number, finishMins: number) : number{
    var durationInMinutes: number;
    durationInMinutes = (finishHour - startHour) * 60 + finishMins - startMins;
    return durationInMinutes;
  }

  checkHours() {
    const hour1 = parseInt(this.selectedHour);
    const hour2 = parseInt(this.selectedHour2);
    const minutes1 = parseInt(this.selectedMinutes);
    const minutes2 = parseInt(this.selectedMinutes2);

    var durationInMinutes: number;

    console.log(this.selectedHour, this.selectedMinutes, this.selectedHour2, this.selectedMinutes2, this.selectedDate)
    // console.log(new Date().toString())

    if (hour1 > hour2) {
      alert('Finish time must be after start time, please select again');
      return;
    }
    else if (hour1 === hour2) {
      if (minutes1 > minutes2) {
        alert('Finish time must be after start time, please select again');
        return;
      }
      else if (minutes1 === minutes2) {
        alert('Appointment can not have duration 0 minutes, please select again');
        return;
      }
    }


    let startDate = new Date();
    startDate.setFullYear(this.selectedDate.getFullYear());
    startDate.setMonth(this.selectedDate.getMonth());
    startDate.setDate(this.selectedDate.getDate());
    startDate.setHours(hour1+2);
    startDate.setMinutes(minutes1);
    startDate.setSeconds(0);
    startDate.setMilliseconds(0);

    let finishDate = new Date();
    finishDate.setFullYear(this.selectedDate.getFullYear());
    finishDate.setMonth(this.selectedDate.getMonth());
    finishDate.setDate(this.selectedDate.getDate());
    finishDate.setHours(hour2+2);
    finishDate.setMinutes(minutes2);
    finishDate.setSeconds(0);
    finishDate.setMilliseconds(0);
    
    var predefinedAppointmentDto : PredefinedAppointmentDto = {
      companyAdminFullName: '',
      duration: this.calculateDuration(hour1, minutes1, hour2, minutes2).toString(),
      startDateAndTime: startDate,
      finishDateAndTime: finishDate
    }

    this.appointmentService.addPredefinedAppointment(predefinedAppointmentDto).subscribe(
      (data)=> {
          this.isSuccessful = data;
          if(this.isSuccessful){
            alert("You successfuly added new appointment");
          }
          else{
            alert("You cant add appointmnet because it overpalses with other appointments");
          }
      }
    )

  }

}



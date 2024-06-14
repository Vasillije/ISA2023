import { Component } from '@angular/core';
import { ComplaintService } from '../../services/complaint.service';
import { AppointmentService } from '../../services/appointment.service';
import { ActivatedRoute } from '@angular/router';
import { Company } from '../../model/company';
import { User } from '../../model/user';

@Component({
  selector: 'app-make-complaint',
  templateUrl: './make-complaint.component.html',
  styleUrl: './make-complaint.component.css'
})
export class MakeComplaintComponent {

  userId! : number;
  companies : Company[] = [];
  companyAdminsList : User[] = [];
  selectedMenuItem: string = '';

  constructor(private complaintService : ComplaintService, private appointmentService : AppointmentService, private route: ActivatedRoute){}

  ngOnInit(): void {
    this.route.paramMap.subscribe(data => {
      const id = +data.get('id')!;
      this.userId = id;
      this.getUsersCompanies();
      this.getAppointmentsAdmin();
      console.log(this.companies)
      console.log(this.companyAdminsList)
    });
  }


  getUsersCompanies() : void{
    this.complaintService.getUsersCompanies(this.userId).subscribe(
      (data) => {
        this.companies = data;
      }
    );
  }

  getAppointmentsAdmin() : void{
    this.complaintService.getAppointmentsAdmin(this.userId).subscribe(
      (data) => {
        this.companyAdminsList = data;
        //this.companyAdminsList = this.removeDuplicates(data);
      }
    );
  }

  changeMenu(menuItem: string) {
    
    this.selectedMenuItem = menuItem;
  }

  // removeDuplicates(admins: User[]): User[] {
  //   const uniqueUsers = admins.filter((user, index, self) =>
  //     index === self.findIndex((t) => (
  //       t.id === user.id
  //     ))
  //   );
  //   return uniqueUsers;
  // }
}

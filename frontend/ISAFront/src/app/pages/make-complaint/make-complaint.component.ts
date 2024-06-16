import { Component } from '@angular/core';
import { ComplaintService } from '../../services/complaint.service';
import { AppointmentService } from '../../services/appointment.service';
import { ActivatedRoute } from '@angular/router';
import { Company } from '../../model/company';
import { User } from '../../model/user';
import { Complaint } from '../../model/complaint';
import { error } from 'console';
import { HttpErrorResponse } from '@angular/common/http';
import { AuthService } from '../../services/auth.service';
import { ComplaintDto } from '../../dto/ComplaintDto';

@Component({
  selector: 'app-make-complaint',
  templateUrl: './make-complaint.component.html',
  styleUrl: './make-complaint.component.css'
})
export class MakeComplaintComponent {

  userId!: number;
  companies: Company[] = [];
  companyAdminsList: User[] = [];

  companyAdminsList2: User[] = [];
  selectedMenuItem: String = '';
  selectedcompany!: Company;
  selectedAdmin!: User;
  complaintText: string ='';

  constructor(private complaintService: ComplaintService, private appointmentService: AppointmentService, private route: ActivatedRoute, 
              private authService : AuthService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(data => {
      const id = +data.get('id')!;
      this.userId = id;
      this.getUsersCompanies();
      this.companyAdminsList2 = this.getUniqueUsers(this.getAppointmentsAdmin());
      console.log(this.companies)
      console.log(this.companyAdminsList)
    });
  }

  getUniqueUsers(users: User[]): User[] {
    const uniqueIds = new Set(users.map(user => user.id));
    const uniqueUsers = Array.from(uniqueIds).map(id => {
      return users.find(user => user.id === id)!;
    });
    return uniqueUsers;
  }


  getUsersCompanies(): void {
    this.complaintService.getUsersCompanies(this.userId).subscribe(
      (data) => {
        this.companies = data;
      }
    );
  }

  changeMenuCompany(company: Company) {
    console.log('ovo je kompanija iz forme', company);
    this.selectedcompany=company;
  }
  changeMenuAdmin(admin: User) {
    this.selectedAdmin=admin;
  }
  removeDuplicates(users: User[]): User[] {
    return Array.from(new Set(users));
  }

  getAppointmentsAdmin(): User[] {
    this.complaintService.getAppointmentsAdmin(this.userId).subscribe(
      (data) => {
        this.companyAdminsList = data;
        //this.companyAdminsList = this.removeDuplicates(data);
      }
    );
    return this.companyAdminsList;
  }
  submitForm(form: any) {
    console.log(this.authService.getUserId()!)
    var complaintFinal: ComplaintDto = {
      id: 0,
      description: this.complaintText,
      companyComplaint: this.selectedcompany,
      companyAdmin: this.selectedAdmin,
      userId : this.authService.getUserId()!,
      answer : ''
      
    }
    console.log('ovo stize iz forme', complaintFinal);
    this.complaintService.addComplaint(complaintFinal).subscribe(
      (data) => {
        alert('Uspesno ste poslali zalbu.');

      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }
}

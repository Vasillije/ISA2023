import { Component } from '@angular/core';
import { CompanyService } from '../../services/company.service';
import { Company } from '../../model/company';
import { Appointment } from '../../model/appointment';
import { SearchDto } from '../../dto/searchDto';
import { HttpErrorResponse } from '@angular/common/http';
import { AuthService } from '../../services/auth.service';
import { CompanyDto } from '../../dto/CompanyDto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent {

  isOpen =false
  companies:Company[]=[]
  userId : number = this.authService.getUserId()!;

  constructor(private companyService : CompanyService, public authService : AuthService, private router: Router){}

  ngOnInit(): void {
    this.companyService.getAll().subscribe((data) => {
      this.companies = data
      this.authService.userLoginTracker();
    })
  }
  
  search(result:any){
    const s: SearchDto = {
      name: result.name,
      town: result.town 
    };
  
    this.companyService.search(s).subscribe((data) => {
    this.companies = data
    }, (error: HttpErrorResponse) => {
      alert(error.message);
    })
   console.log(result);
  }

  //ovde se ne uzima id iz jwt nego se hardkoduje - ispraviti
  complaintClick(){
    if(this.userId!=null){
    this.router.navigate(['/make-complaint/', this.userId]);
    }
  }

  userProfile(){
    this.router.navigate(['/user-profile/', this.userId]);
  }

  adminsComplaints(){
    this.router.navigate(['/admin-complaints/', this.userId]);
  }

}

import { Component } from '@angular/core';
import { CompanyService } from '../../services/company.service';
import { Company } from '../../model/company';
import { Appointment } from '../../model/appointment';
import { SearchDto } from '../../dto/searchDto';
import { HttpErrorResponse } from '@angular/common/http';
import { AuthService } from '../../services/auth.service';
import { CompanyDto } from '../../dto/CompanyDto';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent {

  isOpen =false
  companies:Company[]=[]
  companiesDto : CompanyDto[] = []

  constructor(private companyService : CompanyService, public authService : AuthService){}

  ngOnInit(): void {
    this.companyService.getAll().subscribe((data) => {
      this.companies = data
      
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

}

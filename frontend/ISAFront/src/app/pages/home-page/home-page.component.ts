import { Component } from '@angular/core';
import { CompanyService } from '../../services/company.service';
import { Company } from '../../model/company';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent {

  isOpen =false
  companies:Company[]=[]

  constructor(private companyService : CompanyService){}

  ngOnInit(): void {
    this.companyService.getAll().subscribe((data) => {
      this.companies = data
    })
  }
}

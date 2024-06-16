import { Component } from '@angular/core';
import { Complaint } from '../../model/complaint';
import { ComplaintService } from '../../services/complaint.service';
import { ActivatedRoute } from '@angular/router';
import { error } from 'node:console';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-complaint-history',
  templateUrl: './complaint-history.component.html',
  styleUrl: './complaint-history.component.css'
})
export class ComplaintHistoryComponent {
  complaints : Complaint[] = [];
  
  userId! : number;

  constructor(private complaintService : ComplaintService, private route: ActivatedRoute){}

  ngOnInit():void{
    this.route.paramMap.subscribe(data => {
      const id = +data.get('id')!;
      this.userId = id;
      this.getComplaintsByUserId(this.userId);
    });
  }

  getComplaintsByUserId(id : number){
    this.complaintService.getComplaintsByUserId(id).subscribe(
    (data) => {
      this.complaints = data;
    },
    (error : HttpErrorResponse)=>{
      alert(error.message)
    }
    )
  }

}

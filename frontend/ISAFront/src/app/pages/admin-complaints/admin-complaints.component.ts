import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ComplaintService } from '../../services/complaint.service';
import { Complaint } from '../../model/complaint';
import { AnswerComplaintDto } from '../../dto/AnswerComplaintDto';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-admin-complaints',
  templateUrl: './admin-complaints.component.html',
  styleUrl: './admin-complaints.component.css'
})
export class AdminComplaintsComponent {
  complaints : Complaint[] = [];
  adminId! : number;
  answerText: string ='';
  selectedComplaintId! : number;

  constructor(private route: ActivatedRoute, private complaintService : ComplaintService){}

  ngOnInit(): void {
    this.route.paramMap.subscribe(data => {
      const id = +data.get('id')!;
      this.getAdminsComplaints(id);
    });
  }

  getAdminsComplaints(id : number){
    this.complaintService.getComplaintByAdminId(id).subscribe(
      (data) => this.complaints = data
    );
  }

  replyComplaint(idd : number, text : string){
    const a: AnswerComplaintDto = {
      id: idd,
      answer: text
    };
    console.log(a);
    this.complaintService.reply(a).subscribe(response => {
      alert('Complaint replied successfully');
    }, (error : HttpErrorResponse) => {
     alert( error.message );
    });
  
  }

  selectComplaint(id: number) {
    this.selectedComplaintId = id;
  }

}

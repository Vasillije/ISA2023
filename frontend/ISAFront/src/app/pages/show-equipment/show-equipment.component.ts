import { Component } from '@angular/core';
import { EquipmentService } from '../../services/equipment.service';
import { Equipment } from '../../model/equipment';
import { ActivatedRoute, Router } from '@angular/router';
import { AppointmentService } from '../../services/appointment.service';
import { PurchaseEquipmentDto } from '../../dto/PurchaseEquipmentDto';
import { error } from 'node:console';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-show-equipment',
  templateUrl: './show-equipment.component.html',
  styleUrl: './show-equipment.component.css'
})
export class ShowEquipmentComponent {

  equipment!: Equipment;
  equipments!: Equipment[];
  equipmentsLeft!: Equipment[];
  oprema!: Equipment[];
  quantities: number[] = [];
  isSucesfullyPurchased!: boolean;
  appointmentId!: number;

  constructor(private route: ActivatedRoute, private router: Router, private equipmentService: EquipmentService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(data => {
      const id = +data.get('id')!;
      this.appointmentId = id;
      this.getEquipmentByCompanyID(id);
    });
  }

  getEquipmentByCompanyID(id: number) {
    this.equipmentService.getByCompanyId(id).subscribe(
      (data) => {
        this.equipments = data;
        this.equipmentsLeft = data;
        this.quantities = new Array(this.equipments.length).fill(0);
      },
      (error) => {
        console.error('There was an error!', error);
      }
    );
  }

  //ovo vrv ne treba
  getAllEquipment() {
    this.equipmentService.getAll().subscribe(
      (data) => {
        this.oprema = data;
      },
      (error) => {
        console.error('There was an error!', error);
      }
    );
  }

  getEquipmentById(id: number): void {
    this.equipmentService.getById(id).subscribe(
      (data: Equipment) => {
        this.equipment = data;
      },
      (error) => {
        console.error('Error fetching equipment:', error);
      }
    );
  }

  validation(result: any): void {
    for (let i = 0; i < this.quantities.length; i++) {
      const quantityValue = this.quantities[i];

      if (isNaN(quantityValue) || quantityValue < 0) {
        alert(`The value that you entered in ${i + 1}. field must be number bigger than 0!`);
        return;
      }
    }

    for (let i = 0; i < this.equipments.length; i++) {
      this.equipments[i].quantity = this.quantities[i];
    }

    const p: PurchaseEquipmentDto = {
      equipments: this.equipments,
      appointmentId: this.appointmentId,
      userId: 1
    };

    console.log(this.equipments)
    console.log(this.appointmentId)

    this.equipmentService.purchase(p).subscribe(
      (data) => {
        this.isSucesfullyPurchased = data
      },
      (error: HttpErrorResponse) => alert(error.message)
      
    );



    if (this.isSucesfullyPurchased == false) {
      alert('you have selected a larger amount of equipment than there is currently available');
      
    }
    else {
      alert('you have succesfully purchased the equipment');
    }
    this.router.navigate(['']);
  }
}

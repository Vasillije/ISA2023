import { Appointment } from "./appointment";
import { Company } from "./company";

export interface Equipment{
    id:number,
    name:string,
    quantity:number,
    companies:Company[],
    appointmentsEquipment:Appointment[]
    }
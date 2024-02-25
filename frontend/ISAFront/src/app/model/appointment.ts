import { Company } from "./company";
import { Equipment } from "./equipment";
import { User } from "./user";

export interface Appointment{
    id:number,
    companyAdminFullName:string,
    duration:string,
    dateAndTime:Date,
    userAppointment:User,
    companyAppointment:Company,
    equipmentsAppointment:Equipment[]
}
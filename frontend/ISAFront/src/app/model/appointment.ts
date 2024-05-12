import { Company } from "./company";
import { Equipment } from "./equipment";
import { User } from "./user";

export interface Appointment{
    id:number,
    companyAdminFullName:string,
    duration:string,
    startDateAndTime:Date,
    finishDateAndTime:Date,
    isBooked:boolean,
    userAppointment:User,
    companyAppointment:Company,
    equipmentsAppointment:Equipment[]
}
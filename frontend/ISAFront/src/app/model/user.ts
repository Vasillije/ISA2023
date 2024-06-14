import { Appointment } from "./appointment";
import { Company } from "./company";
import { Complaint } from "./complaint";
import { Role } from "./role";

export interface User{
    id:number,
    email:string,
    password :string,
    name :string,
    surname:string,
    town:string,
    country:string,
    phoneNumber:string,
    profession:string,
    companyInfo:string,
    enabled:boolean,
    penaltyPoints:number,
    appointments:Appointment[],
    roles:Role[],
    complaints: Complaint[],
    company: Company,
    createdAppointments : Appointment[];
}
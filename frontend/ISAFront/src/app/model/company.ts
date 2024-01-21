import { Appointment } from "./appointment";
import { Complaint } from "./complaint";
import { User } from "./user";


export interface Company{
    id: number,
    name: string,
    address : string,
    description : string,
    avgRating : number,
    freeAppointments: Appointment[],
    adminList: User[],
    complaintList: Complaint[]

}
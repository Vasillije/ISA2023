import { Appointment } from "./appointment";
import { Complaint } from "./complaint";
import { Equipment } from "./equipment";
import { User } from "./user";


export interface Company{
    id: number,
    name: string,
    address : string,
    town:string,
    description : string,
    avgRating : number,
    companyAdminList: User[],
    complaints: Complaint[],
    appointments : Appointment[],
    equipmentsCompany : Equipment[]
}



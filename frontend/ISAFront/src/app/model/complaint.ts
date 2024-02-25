import { Company } from "./company";
import { User } from "./user";

export interface Complaint{
    id:number,
    description:string,
    companyComplaint:Company,
    companyAdmin:User
    }
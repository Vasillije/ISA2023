import { Company } from "../model/company";
import { User } from "../model/user";


export interface ComplaintDto{
    id:number,
    description:string,
    companyComplaint:Company,
    companyAdmin:User,
    userId:number,
    answer:string
    
    }
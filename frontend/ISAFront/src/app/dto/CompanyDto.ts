import { Equipment } from "../model/equipment";
import { User } from "../model/user";

export interface CompanyDto{
    id:number, 
    name:string,
    adress:string,
    town:string,
    description:string,
    avgRating:number,
    companyAdminList:User[]
    equipmentsCompany:Equipment[]
}
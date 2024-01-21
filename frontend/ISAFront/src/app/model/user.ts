import { Complaint } from "./complaint";

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
    complaintList: Complaint[]
}
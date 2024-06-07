import { Equipment } from "../model/equipment";

export interface PurchaseEquipmentDto{
    equipments : Equipment[],
    appointmentId : number
    userId : number
}
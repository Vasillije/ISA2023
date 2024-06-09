export interface CancelAppointmentDto{
    userId : number,
    appointmentId : number
    penaltyPoints : number
    cancelDateAndTime : Date
}
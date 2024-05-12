package com.VasilijeISA.ISA2023.service;

import com.VasilijeISA.ISA2023.dto.PredefinedAppointmentDto;
import com.VasilijeISA.ISA2023.model.Appointment;
import com.VasilijeISA.ISA2023.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    public List<Appointment> getAll(){
        return appointmentRepository.findAll();
    }

    public boolean addPredefinedAppoitment(PredefinedAppointmentDto appointment) {
        List<Appointment> appointments = this.getAll();

        for (Appointment a : appointments) {
            if ((a.getStartDateAndTime().isBefore(appointment.getStartDateAndTime()) &&
                    a.getStartDateAndTime().isBefore(appointment.getFinishDateAndTime()) &&
                    a.getFinishDateAndTime().isAfter(appointment.getStartDateAndTime()) &&
                    a.getFinishDateAndTime().isAfter(appointment.getFinishDateAndTime()))){
                return false;
            }
            else if(a.getStartDateAndTime().isAfter(appointment.getStartDateAndTime()) &&
                    a.getStartDateAndTime().isBefore(appointment.getFinishDateAndTime()) &&
                    a.getFinishDateAndTime().isAfter(appointment.getStartDateAndTime()) &&
                    a.getFinishDateAndTime().isAfter(appointment.getFinishDateAndTime())) {
                return false;
            }
            else if (a.getStartDateAndTime().isBefore(appointment.getStartDateAndTime()) &&
                    a.getStartDateAndTime().isBefore(appointment.getFinishDateAndTime()) &&
                    a.getFinishDateAndTime().isAfter(appointment.getStartDateAndTime()) &&
                    a.getFinishDateAndTime().isBefore(appointment.getFinishDateAndTime())) {
                return false;
            }
            else if (a.getStartDateAndTime().isAfter(appointment.getStartDateAndTime()) &&
                    a.getStartDateAndTime().isBefore(appointment.getFinishDateAndTime()) &&
                    a.getFinishDateAndTime().isAfter(appointment.getStartDateAndTime()) &&
                    a.getFinishDateAndTime().isBefore(appointment.getFinishDateAndTime())) {
                return false;
            }
        }

            Appointment a = new Appointment();
            a.setCompanyAdminFullName(appointment.getCompanyAdminFullName());
            a.setStartDateAndTime(appointment.getStartDateAndTime());
            a.setFinishDateAndTime(appointment.getFinishDateAndTime());
            Duration duration = Duration.between(appointment.getStartDateAndTime(), appointment.getFinishDateAndTime());
            long durationInMinutes = duration.toMinutes();
            String durationAsString = Long.toString(durationInMinutes);
            a.setDuration(durationAsString);
            a.setIsBooked(Boolean.FALSE);
            appointmentRepository.save(a);
            return true;
    }
}

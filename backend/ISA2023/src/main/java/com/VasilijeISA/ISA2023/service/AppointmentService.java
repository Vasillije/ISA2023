package com.VasilijeISA.ISA2023.service;

import com.VasilijeISA.ISA2023.dto.CancelAppointmentDto;
import com.VasilijeISA.ISA2023.dto.PredefinedAppointmentDto;
import com.VasilijeISA.ISA2023.model.Appointment;
import com.VasilijeISA.ISA2023.model.Company;
import com.VasilijeISA.ISA2023.model.User;
import com.VasilijeISA.ISA2023.repository.AppointmentRepository;
import com.VasilijeISA.ISA2023.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private UserRepository userRepository;

    public List<Appointment> getAll(){
        return appointmentRepository.findAll();
    }

    public List<Appointment> getValidByCompanyID(int id){
        Company kompanija = companyService.FindCompanyById(id);
        List<Appointment> appointments = appointmentRepository.findAppointmentsByCompanyAppointment(kompanija);
        List<Appointment> validAppointments = new ArrayList<>();
        for(Appointment a : appointments){
            if(a.getStartDateAndTime().isAfter(LocalDateTime.now())){
                validAppointments.add(a);
            }
        }
        return validAppointments;
    }

    public List<Appointment> getValidScheduledByUserID(Long id){
        User user = userRepository.findById(id).orElse(null);
        List<Appointment> appointments = appointmentRepository.findAppointmentsByUserAppointment(user);
        List<Appointment> scheduledAppointments = new ArrayList<>();
        List<Appointment> validScheduledAppointments = new ArrayList<>();

        for(Appointment a : appointments){
                if(a.getStartDateAndTime().isAfter(LocalDateTime.now())){
                    validScheduledAppointments.add(a);
                }
        }

        for(Appointment a : validScheduledAppointments){
            if(a.getIsBooked()){
                scheduledAppointments.add(a);
            }
        }
        return scheduledAppointments;
    }

    public void cancelAppointment(CancelAppointmentDto c){
        Appointment appointment = appointmentRepository.findById(c.getAppointmentId()).orElse(null);
        appointment.setIsBooked(false);
        appointment.setUserAppointment(null);
        appointment.setEquipmentsAppointment(null);
        appointmentRepository.save(appointment);
        User user = userRepository.findById(c.getUserId()).orElse(null);
        Duration duration = Duration.between(c.getCancelDateAndTime(), appointment.getStartDateAndTime());
        long durationInMinutes = duration.toMinutes();
        if(durationInMinutes < 1440){
            user.setPenaltyPoints(user.getPenaltyPoints() + 2);
        }
        else{
            user.setPenaltyPoints(user.getPenaltyPoints() + 1);
        }
        userRepository.save(user);
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

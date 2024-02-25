package com.VasilijeISA.ISA2023.service;

import com.VasilijeISA.ISA2023.model.Appointment;
import com.VasilijeISA.ISA2023.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    public List<Appointment> getAll(){
        return appointmentRepository.findAll();
    }
}

package com.VasilijeISA.ISA2023.controller;

import com.VasilijeISA.ISA2023.model.Appointment;
import com.VasilijeISA.ISA2023.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/all")
    public ResponseEntity<List<Appointment>> getAll() {
        List<Appointment> appointments = appointmentService.getAll();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
}
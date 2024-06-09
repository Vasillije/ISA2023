package com.VasilijeISA.ISA2023.controller;

import com.VasilijeISA.ISA2023.dto.CancelAppointmentDto;
import com.VasilijeISA.ISA2023.dto.PredefinedAppointmentDto;
import com.VasilijeISA.ISA2023.model.Appointment;
import com.VasilijeISA.ISA2023.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/validByCompanyID/{id}")
    public ResponseEntity<List<Appointment>> getValidByCompanyID(@PathVariable int id) {
        List<Appointment> appointments = appointmentService.getValidByCompanyID(id);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("validScheduledByUserID/{id}")
    public ResponseEntity<List<Appointment>> getValidScheduledByUserID(@PathVariable Long id) {
        List<Appointment> appointments = appointmentService.getValidScheduledByUserID(id);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @PostMapping("/addPredefinedAppointment")
    public Boolean addPredefinedAppointment(@RequestBody PredefinedAppointmentDto dto){
        return appointmentService.addPredefinedAppoitment(dto);
    }

    @PostMapping("/cancelAppointment")
    public ResponseEntity<List<Appointment>> cancelAppointment(@RequestBody CancelAppointmentDto c){
        appointmentService.cancelAppointment(c);
        return this.getValidScheduledByUserID(c.getUserId());
    }

}
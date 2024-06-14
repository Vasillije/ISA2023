package com.VasilijeISA.ISA2023.controller;

import com.VasilijeISA.ISA2023.model.Company;
import com.VasilijeISA.ISA2023.model.Complaint;
import com.VasilijeISA.ISA2023.model.User;
import com.VasilijeISA.ISA2023.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/complaints")
public class ComplaintController {

    @Autowired
    ComplaintService complaintService;

    @GetMapping("/usersCompanies/{id}")
    public ResponseEntity<List<Company>> usersCompanies(@PathVariable Long id){
        List<Company> companies = complaintService.getUsersCompanies(id);
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }
    @PostMapping("/addcomplaint")
    public ResponseEntity<Complaint> search(@RequestBody Complaint complaint){
        Complaint complaint1 = complaintService.addComplaint(complaint);
        return new ResponseEntity<>(complaint1, HttpStatus.OK);
    }

    @GetMapping("/appointmentsAdmin/{id}")
   public ResponseEntity<List<User>> companyAdmin(@PathVariable Long id){
        List<User> companyAdmins = complaintService.getAppointmentsAdmin(id);
        return new ResponseEntity<>(companyAdmins, HttpStatus.OK);
    }
}

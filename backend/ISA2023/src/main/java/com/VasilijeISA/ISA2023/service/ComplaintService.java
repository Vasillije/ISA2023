package com.VasilijeISA.ISA2023.service;

import com.VasilijeISA.ISA2023.dto.AnswerComplaintDto;
import com.VasilijeISA.ISA2023.dto.ComplaintDto;
import com.VasilijeISA.ISA2023.model.Appointment;
import com.VasilijeISA.ISA2023.model.Company;
import com.VasilijeISA.ISA2023.model.Complaint;
import com.VasilijeISA.ISA2023.model.User;
import com.VasilijeISA.ISA2023.repository.AppointmentRepository;
import com.VasilijeISA.ISA2023.repository.ComplaintRepository;
import com.VasilijeISA.ISA2023.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ComplaintService {
    @Autowired
    ComplaintRepository complaintRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    private EmailService emailService;

    public List<Company> getUsersCompanies(Long userId){
        User user = userRepository.findById(userId).orElse(null);
        List<Appointment> appointments = appointmentRepository.findAppointmentsByUserAppointment(user);
        List<Appointment> validAppointments = new ArrayList<>();

        for(Appointment a : appointments){
            if(a.getStartDateAndTime().isBefore(LocalDateTime.now())){
                validAppointments.add(a);
            }
        }

        List<Company> companies = new ArrayList<>();
        for(Appointment a : validAppointments){
            companies.add(a.getCompanyAppointment());
        }
        return companies;
    }

    public List<User> getAppointmentsAdmin(Long userId){
        User user = userRepository.findById(userId).orElse(null);
        List<Appointment> appointments = appointmentRepository.findAppointmentsByUserAppointment(user);
        List<Appointment> validAppointments = new ArrayList<>();

        for(Appointment a : appointments){
            if(a.getStartDateAndTime().isBefore(LocalDateTime.now())){
                validAppointments.add(a);
            }
        }

        List<User> admins = new ArrayList<>();
        for(Appointment a : validAppointments){
            admins.add(a.getCompanyAdmin());
        }
        return admins;
    }
    public Complaint addComplaint(ComplaintDto c){
        User user = userRepository.findById(c.getUserId()).orElse(null);
        Complaint complaint=new Complaint();
        complaint.setCompanyComplaint(c.getCompanyComplaint());
        complaint.setDescription(c.getDescription());
        complaint.setUserId(user);
        complaint.setAnswer(c.getAnswer());
        complaint.setCompanyAdmin(c.getCompanyAdmin());

        emailService.sendSimpleEmail("complaint",c.getDescription());
        return  complaintRepository.save(complaint);
    }

    public List<Complaint> getComplaintByUserId(Long id){
        User user = userRepository.findById(id).orElse(null);
        List<Complaint> complaints = complaintRepository.findComplaintByCompanyAdmin(user);
        List<Complaint> complaints1 = new ArrayList<>();
        for(Complaint c : complaints){
            if(c.getAnswer().isEmpty()){
                complaints1.add(c);
            }
        }
        return complaints1;
    }

    public Complaint replyComplaint(AnswerComplaintDto dto){
        Complaint complaint = complaintRepository.findById(dto.getId()).orElse(null);
        complaint.setAnswer(dto.getAnswer());
        emailService.sendSimpleEmail("Complaint reply",dto.getAnswer());
        complaintRepository.save(complaint);
        return complaint;
    }

    public List<Complaint> getAllComplaintByUserId(Long id){
        User user = userRepository.findById(id).orElse(null);
        return complaintRepository.findComplaintByUserId(user);
    }

}


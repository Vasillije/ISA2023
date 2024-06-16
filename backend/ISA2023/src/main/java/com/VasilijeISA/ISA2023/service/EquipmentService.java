package com.VasilijeISA.ISA2023.service;

import com.VasilijeISA.ISA2023.dto.PurchaseEquipmentDto;
import com.VasilijeISA.ISA2023.model.Appointment;
import com.VasilijeISA.ISA2023.model.Company;
import com.VasilijeISA.ISA2023.model.Equipment;
import com.VasilijeISA.ISA2023.model.User;
import com.VasilijeISA.ISA2023.repository.AppointmentRepository;
import com.VasilijeISA.ISA2023.repository.EquipmentRepository;
import com.VasilijeISA.ISA2023.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentService {
    @Autowired
    EquipmentRepository equipmentRepository;
    @Autowired
    CompanyService companyService;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private EmailService emailService;

    private static final Logger logger = LoggerFactory.getLogger(EquipmentService.class);

    public List<Equipment> getAll(){
        return equipmentRepository.findAll();
    }

    public Equipment getByID(Long id){
        return equipmentRepository.findById(id).orElse(null);
    }

    public List<Equipment> getByCompanyID(int id){
        Company company = companyService.FindCompanyById(id);
        return equipmentRepository.findEquipmentByCompanies(company);
    }


    public void appointmentEquipment(PurchaseEquipmentDto p){
        Long c= p.getAppointmentId();
        Appointment appointment = appointmentRepository.findById(c).orElse(null);
        User user = userRepository.findById(p.getUserId()).orElse(null);
        appointment.setUserAppointment(user);
        appointment.setIsBooked(true);
        List<Equipment> equipments = new ArrayList<>();
        for (Equipment equipment : p.getEquipments()) {
            Equipment existingEquipment = equipmentRepository.findById(equipment.getId()).orElse(null);

            equipments.add(existingEquipment);
            List<Appointment> appointments = existingEquipment.getAppointmentsEquipment();
            appointments.add(appointment);
            existingEquipment.setAppointmentsEquipment(appointments);
          //  appointment.addEquipment(existingEquipment);
        }
        appointment.setEquipmentsAppointment(equipments);
        appointmentRepository.save(appointment);
    }
    Boolean validation(PurchaseEquipmentDto p){

        for(Equipment e : p.getEquipments() ){
            Equipment equipmentFromBase = equipmentRepository.findById(e.getId()).orElse(null);
           if( equipmentFromBase.getQuantity() < e.getQuantity()){
               return false;
           }
        }
        return true;

    }
    public Equipment getEquipmentById(Long id, List<Equipment> equipments){
            for(Equipment e : equipments){
                if(e.getId()==id){
                    return e;
                }
            }
            return null;
    }
    public Boolean purchase(PurchaseEquipmentDto p){
        try {
            if (!validation(p)) {
                return false;
            }
            appointmentEquipment(p);
            Appointment appointment = appointmentRepository.findById(p.getAppointmentId()).orElse(null);
            List<Equipment> equipments = appointment.getEquipmentsAppointment();
            List<String> equipmentInfo= new ArrayList<>();
            for (Equipment e : p.getEquipments()) {
                equipmentInfo.add(e.getName() + " - Kolicina: " + e.getQuantity());
                Equipment equipmentFromBase = getEquipmentById(e.getId(), equipments);
                int currentQuantity = equipmentFromBase.getQuantity();
                currentQuantity = currentQuantity - e.getQuantity();
                equipmentFromBase.setQuantity(currentQuantity);
                equipmentRepository.save(equipmentFromBase);
            }

            //String qrText = htmlService.generateAndReturnHtmlFilePath("ee","fefe","fef");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"); // Primer formata datuma i vremena

            String qrText = String.format("Vaša rezervacija je potvrđena:%nDatum i vreme početka: %s%nDatum i vreme završetka: %s%nOprema:%n%s",
                    appointment.getStartDateAndTime().format(formatter),
                    appointment.getFinishDateAndTime().format(formatter),
                    String.join("\n", equipmentInfo));
            byte[] qrCode = emailService.generateQRCode(qrText, 200, 200);

            emailService.sendEmailWithQRCode("Your Reservation Confirmation", "Here is your QR code with reservation details.", qrCode);

            return true;
        } catch (Exception e) {
            logger.error("Error during purchase operation", e);
            return false;
        }
    }
}

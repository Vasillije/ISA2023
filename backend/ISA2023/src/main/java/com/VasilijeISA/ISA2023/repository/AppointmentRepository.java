package com.VasilijeISA.ISA2023.repository;

import com.VasilijeISA.ISA2023.model.Appointment;
import com.VasilijeISA.ISA2023.model.Company;
import com.VasilijeISA.ISA2023.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    List<Appointment> findAppointmentsByCompanyAppointment(Company company);
    List<Appointment> findAppointmentsByUserAppointment(User user);
}

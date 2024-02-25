package com.VasilijeISA.ISA2023.repository;

import com.VasilijeISA.ISA2023.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

}

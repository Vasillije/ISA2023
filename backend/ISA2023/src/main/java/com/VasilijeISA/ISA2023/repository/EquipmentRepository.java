package com.VasilijeISA.ISA2023.repository;

import com.VasilijeISA.ISA2023.model.Company;
import com.VasilijeISA.ISA2023.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findEquipmentByCompanies(Company company);
}

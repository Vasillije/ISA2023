package com.VasilijeISA.ISA2023.service;

import com.VasilijeISA.ISA2023.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService {
    @Autowired
    EquipmentRepository equipmentRepository;

}

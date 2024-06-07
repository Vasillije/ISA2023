package com.VasilijeISA.ISA2023.dto;

import com.VasilijeISA.ISA2023.model.Equipment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PurchaseEquipmentDto {
    List<Equipment> equipments;
    Long appointmentId;
    Long userId;
}

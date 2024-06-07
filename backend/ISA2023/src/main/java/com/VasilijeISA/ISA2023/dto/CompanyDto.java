package com.VasilijeISA.ISA2023.dto;

import com.VasilijeISA.ISA2023.model.Equipment;
import com.VasilijeISA.ISA2023.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompanyDto {
    private Long id;
    private String name;
    private String address;
    private String town;
    private String description;
    private float avgRating;
    private List<User> companyAdminList;
    private List<Equipment> equipmentsCompany;


}

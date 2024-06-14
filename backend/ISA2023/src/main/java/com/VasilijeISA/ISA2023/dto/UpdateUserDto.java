package com.VasilijeISA.ISA2023.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateUserDto {
    private Long id;
    private String password;
    private String name;
    private String surname;
    private String town;
    private String country;
    private String phoneNumber;
    private String profession;
    private String companyInfo;
}
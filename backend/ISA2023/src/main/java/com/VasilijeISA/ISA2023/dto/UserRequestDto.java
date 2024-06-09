package com.VasilijeISA.ISA2023.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String password;
    private String name;
    private String surname;
    private String email;
    private String city;
    private String country;
    private String phoneNumber;
    private String profession;
    private String companyInfo;
}

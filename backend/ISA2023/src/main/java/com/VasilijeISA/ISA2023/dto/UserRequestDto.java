package com.VasilijeISA.ISA2023.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

    private Long id;

    private String password;

    private String name;

    private String surname;

    private String email;

}

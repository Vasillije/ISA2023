package com.VasilijeISA.ISA2023.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PredefinedAppointmentDto {
    private String companyAdminFullName;
    private String duration;
    private LocalDateTime startDateAndTime;
    private LocalDateTime finishDateAndTime;
}

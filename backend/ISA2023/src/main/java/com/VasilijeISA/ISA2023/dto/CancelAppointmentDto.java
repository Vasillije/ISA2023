package com.VasilijeISA.ISA2023.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CancelAppointmentDto {
    private Long userId;
    private Long AppointmentId;
    private int penaltyPoints;
    private LocalDateTime cancelDateAndTime;
}

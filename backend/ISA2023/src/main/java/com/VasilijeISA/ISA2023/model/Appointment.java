package com.VasilijeISA.ISA2023.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyAdminFullName;
    private String duration;
    private LocalDateTime startDateAndTime;
    private LocalDateTime finishDateAndTime;
    private Boolean isBooked = false;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User userAppointment;

    @ManyToOne
    @JoinColumn(name="company_id")
    private Company companyAppointment;

     @ManyToMany(mappedBy = "appointmentsEquipment")
    private List<Equipment> equipmentsAppointment;
}

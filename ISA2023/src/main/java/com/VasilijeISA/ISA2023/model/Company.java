package com.VasilijeISA.ISA2023.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Table(name="companies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String description;
    private String avgRating;
    @OneToMany
    @JoinColumn(name = "company_id")
    private List<Appointment> freeAppointments;
    @OneToMany
    @JoinColumn(name = "company_id")
    private List<User> adminList;
    @OneToMany
    @JoinColumn(name = "company_id")
    private List<Complaint> complaintList; // ovo je bidirekciono ne moze ovako imas listu ovde

}

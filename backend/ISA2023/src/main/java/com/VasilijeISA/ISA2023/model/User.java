package com.VasilijeISA.ISA2023.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email; //username
    private String password;
    private String name;
    private String surname;
    private String town;
    private String country;
    private String phoneNumber;
    private String profession;
    private String companyInfo;
    // private int penaltyPoints;
    //private enum userCategory;
    //ovo polje imaju registrovani korisnik i system admin i company admin
    @OneToMany(mappedBy = "userAppointment")
    List<Appointment> appointments;
    // @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "company_id", nullable = true)
    //@OneToMany
    //@JoinColumn(name = "user_id")
    //private List<Complaint> complaintList;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    //ovo polje imaju registrovani korisnik i system admin
    @OneToMany(mappedBy = "companyAdmin")
    private List<Complaint> complaints;

    //ovo polje imaju company admin i system admin
    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;
}

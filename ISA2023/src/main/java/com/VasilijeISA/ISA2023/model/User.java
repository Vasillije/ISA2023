package com.VasilijeISA.ISA2023.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
   // @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "company_id", nullable = true)
    //private Company company;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Complaint> complaintList;

}

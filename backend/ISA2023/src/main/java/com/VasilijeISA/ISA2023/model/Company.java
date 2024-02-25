package com.VasilijeISA.ISA2023.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @Column(name = "companyId")
    private Long id;

    private String name;
    private String address;
    private String town;
    private String description;
    private float avgRating;

    @OneToMany
    @JoinColumn(name = "company_id")
    private List<User> companyAdminList;
    /* @OneToMany
    @JoinColumn(name = "company_id")
    private List<Complaint> complaintList; // ovo je bidirekciono ne moze ovako imas listu ovde
    */
    @OneToMany(mappedBy = "companyComplaint")
    private List<Complaint> complaints;

    @OneToMany(mappedBy = "companyAppointment")
    private List<Appointment> appointments;

    @ManyToMany
    @JoinTable(name = "company_eqipment",
            joinColumns = @JoinColumn(name = "company_id", referencedColumnName = "companyId"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id",
                    referencedColumnName = "id"))
    private List<Equipment> equipmentsCompany;

}

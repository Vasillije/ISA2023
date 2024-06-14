package com.VasilijeISA.ISA2023.model;

import com.VasilijeISA.ISA2023.dto.CompanyDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<User> companyAdminList;
    /* @OneToMany
    @JoinColumn(name = "company_id")
    private List<Complaint> complaintList; // ovo je bidirekciono ne moze ovako imas listu ovde
    */
    @OneToMany(mappedBy = "companyComplaint")
    @JsonIgnore
    private List<Complaint> complaints;

    @OneToMany(mappedBy = "companyAppointment")
    @JsonIgnore
    private List<Appointment> appointments;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "company_eqipments",
            joinColumns = @JoinColumn(name = "company_id", referencedColumnName = "companyId"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id",
                    referencedColumnName = "id"))
    private List<Equipment> equipmentsCompany;

    public CompanyDto companyDtoMapper(Company company){
        CompanyDto companyDto = new CompanyDto(
                id = company.getId(),
                name = company.getName(),
                address = company.getAddress(),
                town = company.getTown(),
                description = company.getDescription(),
                avgRating = company.getAvgRating(),
                companyAdminList = company.getCompanyAdminList(),
                equipmentsCompany = company.getEquipmentsCompany()
        );
        return companyDto;
    }
}


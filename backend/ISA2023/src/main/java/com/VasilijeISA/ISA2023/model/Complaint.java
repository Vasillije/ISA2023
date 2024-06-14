package com.VasilijeISA.ISA2023.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="users_complaints")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToOne
    @JoinColumn(name="company_id")
    private Company companyComplaint;

    @ManyToOne
    @JoinColumn(name="companyAdmin_id")
    private User companyAdmin;
}

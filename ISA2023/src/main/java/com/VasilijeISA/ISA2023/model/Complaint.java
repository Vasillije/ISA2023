package com.VasilijeISA.ISA2023.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="complaints")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   // private Company company;
   // private User companyAdmin;
  //  private String description;
}

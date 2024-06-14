package com.VasilijeISA.ISA2023.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "equipments")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int quantity;

    @ManyToMany(mappedBy = "equipmentsCompany")
    private List<Company> companies;

 /*   @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "equipment_appointment",
            joinColumns = @JoinColumn(name = "appointment_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id",
                    referencedColumnName = "id"))
    private List<Appointment> appointmentsEquipment;*/

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "equipments_appointment",
            joinColumns = @JoinColumn(name = "equipment_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "appointment_id", referencedColumnName = "id"))
    private List<Appointment> appointmentsEquipment;



}

package com.VasilijeISA.ISA2023.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

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
    @Column(name = "enabled")
    private boolean enabled;
    private int penaltyPoints;
   @OneToMany(mappedBy = "userAppointment")
   @JsonIgnore
   List<Appointment> appointments;

    // @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "company_id", nullable = true)
    //@OneToMany
    //@JoinColumn(name = "user_id")
    //private List<Complaint> complaintList;

    //@ManyToOne
    //@JoinColumn(name = "role_id")
    //private Role role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;


    //ovo polje imaju registrovani korisnik i system admin
    @OneToMany(mappedBy = "companyAdmin")
    @JsonIgnore
    private List<Complaint> complaints;

    @OneToMany(mappedBy = "userId")
    @JsonIgnore
    private List<Complaint> userComplaints;

    //ovo polje imaju company admin i system admin
    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;

    private String activationToken;
    private boolean emailConfirmed=false;

    @JsonIgnore
    @OneToMany(mappedBy = "companyAdmin")
    private List<Appointment> createdAppointments;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return email;
    }


    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User(Long id, String email, String password, String name, String surname, String town, String country, String phoneNumber, String profession, String companyInfo, boolean enabled, int penaltyPoints, List<Appointment> appointments, List<Role> roles, List<Complaint> complaints, Company company, String activationToken, List<Appointment> createdAppointments) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.town = town;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.profession = profession;
        this.companyInfo = companyInfo;
        this.enabled = enabled;
        this.penaltyPoints = penaltyPoints;
        this.appointments = appointments;
        this.roles = roles;
        this.complaints = complaints;
        this.company = company;
        this.activationToken = activationToken;
        this.emailConfirmed = false;
        this.createdAppointments = createdAppointments;
    }
}

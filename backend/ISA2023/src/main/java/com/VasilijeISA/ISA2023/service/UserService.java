package com.VasilijeISA.ISA2023.service;

import com.VasilijeISA.ISA2023.dto.UpdateUserDto;
import com.VasilijeISA.ISA2023.dto.UserRequestDto;
import com.VasilijeISA.ISA2023.exeption.UserAlreadyExistsException;
import com.VasilijeISA.ISA2023.model.Role;
import com.VasilijeISA.ISA2023.model.User;
import com.VasilijeISA.ISA2023.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    public User findByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }
    public User save(UserRequestDto userRequest) {
        User existingUser = userRepository.findByEmail(userRequest.getEmail());
        if(existingUser != null){
            throw new UserAlreadyExistsException("User with email " + userRequest.getEmail() + " already exists");
        }
        User u = new User();
        u.setEmail(userRequest.getEmail());
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        u.setName(userRequest.getName());
        u.setSurname(userRequest.getSurname());
        u.setEnabled(true);
        List<Role> roles = roleService.findByName("ROLE_USER");
        u.setRoles(roles);
        u.setTown(userRequest.getCity());
        u.setCountry(userRequest.getCountry());
        u.setPhoneNumber(userRequest.getPhoneNumber());
        u.setProfession(userRequest.getProfession());
        u.setCompanyInfo(userRequest.getCompanyInfo());
        u.setPenaltyPoints(0);
        return this.userRepository.save(u);
    }

    public User update(UpdateUserDto u){
        User user = userRepository.findById(u.getId()).orElse(null);
        user.setPassword(passwordEncoder.encode(u.getPassword()));
        user.setName(u.getName());
        user.setSurname(u.getSurname());
        user.setTown(u.getTown());
        user.setCountry(u.getCountry());
        user.setPhoneNumber(u.getPhoneNumber());
        user.setProfession(u.getProfession());
        user.setCompanyInfo(u.getCompanyInfo());
        userRepository.save(user);
        return user;
    }


    private String town;
    private String country;
    private String phoneNumber;
    private String profession;
    private String companyInfo;

}
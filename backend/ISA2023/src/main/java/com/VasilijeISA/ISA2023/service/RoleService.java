package com.VasilijeISA.ISA2023.service;

import com.VasilijeISA.ISA2023.model.Role;
import com.VasilijeISA.ISA2023.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> findByName(String roleUser) {
      return  roleRepository.findByName(roleUser);
    }
}

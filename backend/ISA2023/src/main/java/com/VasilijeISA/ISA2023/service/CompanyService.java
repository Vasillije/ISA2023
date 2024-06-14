package com.VasilijeISA.ISA2023.service;

import com.VasilijeISA.ISA2023.dto.CompanyDto;
import com.VasilijeISA.ISA2023.dto.SearchDto;
import com.VasilijeISA.ISA2023.model.Company;
import com.VasilijeISA.ISA2023.repository.AppointmentRepository;
import com.VasilijeISA.ISA2023.repository.CompanyRepository;
import com.VasilijeISA.ISA2023.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Company> getAll() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyDto> companiesDto = new ArrayList<>();
        for(Company c : companies){
            companiesDto.add(c.companyDtoMapper(c));
        }
        return companies;
    }
    public Company FindCompanyById(int id){
        List<Company> kompanije = companyRepository.findAll();

        for(Company c : kompanije){
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }

    public List<Company> search(SearchDto dto) {
        String name = dto.getName();
        String town= dto.getTown();

        if (name.equals("")){
            return companyRepository.findCompaniesByTown(town);
        }
        if(town.equals("")){
            return companyRepository.findCompaniesByName(name);
        }

        return companyRepository.search(name, town);
    }

}

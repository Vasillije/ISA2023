package com.VasilijeISA.ISA2023.service;

import com.VasilijeISA.ISA2023.dto.SearchDto;
import com.VasilijeISA.ISA2023.model.Company;
import com.VasilijeISA.ISA2023.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public List<Company> search(SearchDto dto) {
        String name = dto.getName();
        String town= dto.getTown();

        if (name.equals("")){
            return companyRepository.findCompaniesByTown(town);
        }
        if(town.equals("")){
            return companyRepository.findCompaniesByName(name);
        };

        return companyRepository.search(name, town);
    }
}

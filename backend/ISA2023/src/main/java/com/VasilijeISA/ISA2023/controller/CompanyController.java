package com.VasilijeISA.ISA2023.controller;


import com.VasilijeISA.ISA2023.dto.SearchDto;
import com.VasilijeISA.ISA2023.model.Company;
import com.VasilijeISA.ISA2023.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;


    @GetMapping("/all")
    public ResponseEntity<List<Company>> getAll(){
        List<Company> companies = companyService.getAll();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Company>> search(@RequestBody SearchDto dto){
        List<Company> companies = companyService.search(dto);
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

}

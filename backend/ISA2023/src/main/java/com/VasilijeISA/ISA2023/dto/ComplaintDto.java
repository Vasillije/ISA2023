package com.VasilijeISA.ISA2023.dto;

import com.VasilijeISA.ISA2023.model.Company;
import com.VasilijeISA.ISA2023.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintDto {
    private Long id;
    private String description;
    private Company companyComplaint;
    private User companyAdmin;
    private Long userId;

    private String answer;


}


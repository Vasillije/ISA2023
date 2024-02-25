package com.VasilijeISA.ISA2023.service;

import com.VasilijeISA.ISA2023.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintService {
    @Autowired
    ComplaintRepository complaintRepository;


}

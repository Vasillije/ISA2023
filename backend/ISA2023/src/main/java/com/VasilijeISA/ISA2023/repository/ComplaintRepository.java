package com.VasilijeISA.ISA2023.repository;

import com.VasilijeISA.ISA2023.model.Complaint;
import com.VasilijeISA.ISA2023.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findComplaintByCompanyAdmin(User user);
    List<Complaint> findComplaintByUserId(User user);
}

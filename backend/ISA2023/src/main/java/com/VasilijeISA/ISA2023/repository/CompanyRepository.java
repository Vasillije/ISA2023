package com.VasilijeISA.ISA2023.repository;

import com.VasilijeISA.ISA2023.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("select c from Company c where upper(c.name)=upper(:name) and upper(c.town)=upper(:town)")
    List<Company> search(String name, String town);
    @Query("select c from Company c where upper(c.name)=upper(:name)")
    List<Company> findCompaniesByName (String name);
    @Query("select c from Company c where upper(c.town)=upper(:town)")
    List<Company> findCompaniesByTown (String town);


}

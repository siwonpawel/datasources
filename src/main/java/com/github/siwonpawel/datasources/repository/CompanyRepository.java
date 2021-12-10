package com.github.siwonpawel.datasources.repository;

import com.github.siwonpawel.datasources.model.administrative.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}

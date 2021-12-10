package com.github.siwonpawel.datasources.service;

import com.github.siwonpawel.datasources.model.administrative.Company;
import com.github.siwonpawel.datasources.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepo;

    public List<Company> getAll() {
        return companyRepo.findAll();
    }
}

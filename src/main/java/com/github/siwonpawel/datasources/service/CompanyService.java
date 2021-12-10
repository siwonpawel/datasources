package com.github.siwonpawel.datasources.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.siwonpawel.datasources.model.administrative.Company;
import com.github.siwonpawel.datasources.repository.CompanyRepository;

@Service
public class CompanyService
{

    @Autowired
    private CompanyRepository companyRepo;

    public List<Company> getAll()
    {
        return companyRepo.findAll();
    }

    public Company add(Company company)
    {
        return companyRepo.save(company);
    }

    public Company updateById(Long id, Company company)
    {
        return companyRepo.findById(id)
                .map(d -> {
                    BeanUtils.copyProperties(company, d);
                    return d;
                })
                .map(companyRepo::save)
                .orElseThrow(() -> new NoSuchElementException(""));
    }

    public void deleteById(Long id)
    {
        companyRepo.deleteById(id);
    }

    public Company getById(Long companyId)
    {
        return companyRepo.getById(companyId);
    }
}

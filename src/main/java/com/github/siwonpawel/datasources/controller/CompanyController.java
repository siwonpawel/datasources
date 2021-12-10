package com.github.siwonpawel.datasources.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.siwonpawel.datasources.model.administrative.Company;
import com.github.siwonpawel.datasources.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController
{

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<Company> getAll()
    {
        return companyService.getAll();
    }

    @PostMapping
    public Company create(@RequestBody Company company)
    {
        return companyService.add(company);
    }

    @PutMapping("/{companyId}")
    public Company update(@PathVariable Long companyId, @RequestBody Company company)
    {
        return companyService.updateById(companyId, company);
    }

    @GetMapping("/{companyId}")
    public Company getById(@PathVariable Long companyId)
    {
        return companyService.getById(companyId);
    }

    @DeleteMapping("/{companyId}")
    public void delete(@PathVariable Long companyId)
    {
        companyService.deleteById(companyId);
    }

}

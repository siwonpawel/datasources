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

import com.github.siwonpawel.datasources.model.administrative.Advertisement;
import com.github.siwonpawel.datasources.service.AdvertisementService;

@RestController
@RequestMapping("/company/{companyId}/advertisement")
public class AdvertisementController
{

    @Autowired
    private AdvertisementService advertisementService;

    @PostMapping
    public Advertisement add(@PathVariable Long companyId, @RequestBody Advertisement advertisement)
    {
        return advertisementService.add(companyId, advertisement);
    }

    @GetMapping("/{advertisementId}")
    public Advertisement getById(@PathVariable Long advertisementId)
    {
        return advertisementService.getById(advertisementId);
    }

    @PutMapping("/{advertisementId}")
    public Advertisement update(@PathVariable Long companyId, @PathVariable Long advertisementId, @RequestBody Advertisement advertisement)
    {
        return advertisementService.updateById(advertisementId, advertisement);
    }

    @DeleteMapping("/{advertisementId}")
    public void delete(@PathVariable Long advertisementId)
    {
        advertisementService.deleteById(advertisementId);
    }

    @GetMapping
    public List<Advertisement> getAll()
    {
        return advertisementService.getAll();
    }
}

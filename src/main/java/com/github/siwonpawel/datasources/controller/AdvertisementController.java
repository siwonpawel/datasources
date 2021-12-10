package com.github.siwonpawel.datasources.controller;

import com.github.siwonpawel.datasources.model.administrative.Advertisement;
import com.github.siwonpawel.datasources.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/advertisement")
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @GetMapping
    public List<Advertisement> getAll() {
        return advertisementService.getAll();
    }
}

package com.github.siwonpawel.datasources.service;

import com.github.siwonpawel.datasources.model.administrative.Advertisement;
import com.github.siwonpawel.datasources.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementService {

    @Autowired
    private AdvertisementRepository advertisementRepo;

    public List<Advertisement> getAll() {
        return advertisementRepo.findAll();
    }
}

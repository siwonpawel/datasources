package com.github.siwonpawel.datasources.service;

import com.github.siwonpawel.datasources.model.advertisement.Data;
import com.github.siwonpawel.datasources.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {

    @Autowired
    private DataRepository dataRepo;

    public List<Data> findAll() {
        return dataRepo.findAll();
    }
}

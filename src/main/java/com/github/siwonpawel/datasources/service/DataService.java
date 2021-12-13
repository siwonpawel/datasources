package com.github.siwonpawel.datasources.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.siwonpawel.datasources.model.advertisement.Data;
import com.github.siwonpawel.datasources.repository.DataRepository;

@Service
public class DataService
{

    @Autowired
    private DataRepository dataRepo;

    public List<Data> findAll()
    {
        return dataRepo.findAll();
    }

    public Data add(com.github.siwonpawel.datasources.model.advertisement.Data data)
    {
        return dataRepo.save(data);
    }

    public Data updateById(Long id, com.github.siwonpawel.datasources.model.advertisement.Data data)
    {
        return dataRepo.findById(id)
                .map(d -> {
                    BeanUtils.copyProperties(data, d);
                    return d;
                })
                .map(dataRepo::save)
                .orElseThrow(() -> new NoSuchElementException(""));
    }

    public Data getById(Long dataId)
    {
        return dataRepo.getById(dataId);
    }

    public void deleteById(Long dataId)
    {
        dataRepo.deleteById(dataId);
    }
}

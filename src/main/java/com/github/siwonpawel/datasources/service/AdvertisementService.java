package com.github.siwonpawel.datasources.service;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.siwonpawel.datasources.configuration.datasource.DataSourceProperties;
import com.github.siwonpawel.datasources.configuration.datasource.DataSourceRouter;
import com.github.siwonpawel.datasources.model.administrative.Advertisement;
import com.github.siwonpawel.datasources.model.administrative.Company;
import com.github.siwonpawel.datasources.repository.AdvertisementRepository;
import com.github.siwonpawel.datasources.repository.CompanyRepository;

@Service
public class AdvertisementService
{

    @Autowired
    private AdvertisementRepository advertisementRepo;
    @Autowired
    private CompanyRepository companyRepo;
    @Autowired
    private DataSourceProperties datasourceProperties;
    @Autowired
    private DataSourceRouter dataSourceRouter;

    public List<Advertisement> getAll()
    {
        return advertisementRepo.findAll();
    }

    public Advertisement add(Long companyId, Advertisement advertisement)
    {
        advertisement.setCompany(Company.builder().id(companyId).build());
        advertisement = advertisementRepo.save(advertisement);
        try (
                var conn = dataSourceRouter.getConnection();
                var stmt = conn.createStatement()
        )
        {
            var dbName = String.format(
                    "c_%d_db_%d",
                    advertisement.getCompany().getId(),
                    advertisement.getId()
            );


            stmt.execute(String.format("CREATE DATABASE %s", dbName));
            stmt.execute(String.format("CREATE TABLE %s.data( id INT PRIMARY KEY AUTO_INCREMENT, value VARCHAR(20), description VARCHAR(50))", dbName));
            stmt.execute(String.format("INSERT INTO %s.data(value, description) VALUES('%s', 'created on database initialization')", dbName, dbName));

            advertisement.setDbName(dbName);
            return advertisementRepo.save(advertisement);
        }
        catch (SQLException e)
        {
            throw new IllegalStateException("Unknown error creating database.", e);
        }
    }

    public Advertisement updateById(Long id, Advertisement advertisement)
    {
        return advertisementRepo.findById(id)
                .map(d -> {
                    BeanUtils.copyProperties(advertisement, d);
                    return d;
                })
                .map(advertisementRepo::save)
                .orElseThrow(() -> new NoSuchElementException(""));
    }

    public void deleteById(Long id)
    {
        advertisementRepo.deleteById(id);
    }

    public Advertisement getById(Long advertisementId)
    {
        return advertisementRepo.getById(advertisementId);
    }
}

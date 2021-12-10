package com.github.siwonpawel.datasources.service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.sql.DataSource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.siwonpawel.datasources.configuration.datasource.DataSourceProperties;
import com.github.siwonpawel.datasources.configuration.datasource.DatasourceConfiguration;
import com.github.siwonpawel.datasources.model.administrative.Advertisement;
import com.github.siwonpawel.datasources.repository.AdvertisementRepository;

@Service
public class AdvertisementService implements InitializingBean
{

    @Autowired
    private AdvertisementRepository advertisementRepo;
    @Autowired
    private DataSourceProperties datasourceProperties;
    @Autowired
    private Map<Object, DataSource> companyDatabases;

    public List<Advertisement> getAll()
    {
        return advertisementRepo.findAll();
    }

    public Advertisement add(Advertisement advertisement)
    {
        return advertisementRepo.save(advertisement);
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

    @Override public void afterPropertiesSet() throws Exception
    {
        advertisementRepo.findAll()
                .forEach(advertisement -> companyDatabases.put(
                        advertisement.getDbName(),
                        DatasourceConfiguration.createDataSource(
                                datasourceProperties.getUrl() + "/" + advertisement.getDbName(),
                                datasourceProperties.getUsername(),
                                datasourceProperties.getPassword()
                        ))
                );
    }

    public Advertisement getById(Long advertisementId)
    {
        return advertisementRepo.getById(advertisementId);
    }
}

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

import com.github.siwonpawel.datasources.configuration.datasource.routing.RoutedDataSource;
import com.github.siwonpawel.datasources.configuration.datasource.routing.RoutingKey;
import com.github.siwonpawel.datasources.model.advertisement.Data;
import com.github.siwonpawel.datasources.service.DataService;
import lombok.extern.log4j.Log4j2;
import static com.github.siwonpawel.datasources.configuration.datasource.routing.RoutingKey.RoutingKeyValue.ADVERTISEMENT_ID;
import static com.github.siwonpawel.datasources.configuration.datasource.routing.RoutingKey.RoutingKeyValue.COMPANY_ID;

@Log4j2
@RestController
@RequestMapping("/company/{companyId}/advertisement/{advertisementId}/data")
public class DataController
{

    @Autowired
    private DataService dataService;

    @RoutedDataSource
    @GetMapping
    public List<Data> getAllData(
            @PathVariable @RoutingKey(COMPANY_ID) Integer companyId,
            @PathVariable @RoutingKey(ADVERTISEMENT_ID) Integer advertisementId)
    {
        log.info("company: {}, advertisement: {}", companyId, advertisementId);
        return dataService.findAll();
    }

    @RoutedDataSource
    @PostMapping
    public Data create(
            @PathVariable @RoutingKey(COMPANY_ID) Integer companyId,
            @PathVariable @RoutingKey(ADVERTISEMENT_ID) Integer advertisementId,
            @RequestBody Data data)
    {
        log.info("company: {}, advertisement: {}", companyId, advertisementId);
        return dataService.add(data);
    }

    @PutMapping("/{dataId}")
    public Data update(
            @PathVariable @RoutingKey(COMPANY_ID) Integer companyId,
            @PathVariable @RoutingKey(ADVERTISEMENT_ID) Integer advertisementId,
            @PathVariable Long dataId,
            @RequestBody Data data)
    {
        return dataService.updateById(dataId, data);
    }

    @GetMapping("/{dataId}")
    public Data getById(
            @PathVariable @RoutingKey(COMPANY_ID) Integer companyId,
            @PathVariable @RoutingKey(ADVERTISEMENT_ID) Integer advertisementId,
            @PathVariable Long dataId)
    {
        return dataService.getById(dataId);
    }

    @DeleteMapping("/{dataId}")
    public void delete(
            @PathVariable @RoutingKey(COMPANY_ID) Integer companyId,
            @PathVariable @RoutingKey(ADVERTISEMENT_ID) Integer advertisementId,
            @PathVariable Long dataId)
    {
        dataService.deleteById(dataId);
    }

}

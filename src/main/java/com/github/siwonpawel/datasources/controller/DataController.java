package com.github.siwonpawel.datasources.controller;

import com.github.siwonpawel.datasources.model.advertisement.Data;
import com.github.siwonpawel.datasources.service.DataService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping("/{company}/{advertisement}")
    public List<Data> getAllData(@PathVariable Integer company, @PathVariable Integer advertisement) {
        log.info("company: {}, advertisement: {}", company, advertisement);
        return dataService.findAll();
    }

}

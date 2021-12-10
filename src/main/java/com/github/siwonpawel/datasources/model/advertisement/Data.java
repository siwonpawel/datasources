package com.github.siwonpawel.datasources.model.advertisement;

import javax.persistence.*;

@lombok.Data
@Entity
public class Data {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String databaseName;

}

package com.github.siwonpawel.datasources.model.administrative;

import lombok.Data;

import javax.persistence.*;

@lombok.Data
@Entity
public class Company
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String url;

}

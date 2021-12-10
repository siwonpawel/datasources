package com.github.siwonpawel.datasources.model.administrative;

import javax.persistence.*;

@lombok.Data
@Entity
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String dbName;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Company company;

}

package com.github.siwonpawel.datasources.repository;

import com.github.siwonpawel.datasources.model.advertisement.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {
}

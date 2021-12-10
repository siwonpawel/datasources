package com.github.siwonpawel.datasources.repository;

import com.github.siwonpawel.datasources.model.administrative.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {



}

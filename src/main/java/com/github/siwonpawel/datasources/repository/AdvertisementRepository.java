package com.github.siwonpawel.datasources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.siwonpawel.datasources.model.administrative.Advertisement;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>
{
}

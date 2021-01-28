package isa.apoteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.domain.City;

public interface CityRepository extends JpaRepository<City, Long>{
    
    @Transactional
    @Query("from City c join c.country co where co.id=?1")
	List<City> findAllCitiesForCountry(Long id);
}
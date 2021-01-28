package isa.apoteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.domain.Address;
import isa.apoteka.domain.City;
import isa.apoteka.domain.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{
    
    @Query(value = "select * from Country", nativeQuery = true)
	List<Country> findAllCountries();
}
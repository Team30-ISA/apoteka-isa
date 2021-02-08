package isa.apoteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import isa.apoteka.domain.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{
    
	@Query(value = "select * from Country", nativeQuery = true)
	List<Country> findAllCountries();
}
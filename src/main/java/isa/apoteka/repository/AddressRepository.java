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


public interface AddressRepository extends JpaRepository<Address, Long>{
    
    @Transactional
    @Query("from Address a join a.city c where c.id=?1")
	List<Address> findAllAddressesForCity(Long id);
}

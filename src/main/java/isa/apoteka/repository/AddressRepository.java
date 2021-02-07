package isa.apoteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.domain.Address;


public interface AddressRepository extends JpaRepository<Address, Long>{
    
    @Query("from Address a join a.city c where c.id=?1")
	List<Address> findAllAddressesForCity(Long id);
    
    @Modifying
    @Query("update Address a set a.street = ?1, a.city.id = ?2 where a.id = ?3")
    void update(String street, Long cityId, Long id);
   
}

package isa.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.domain.Pharmacist;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {

	@Modifying
    @Transactional
    @Query("update Pharmacist p set p.firstName = ?1, p.lastName = ?2 where p.id = ?3")
    void update(String firstName, String lastName, Long id);
}

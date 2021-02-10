package isa.apoteka.repository;

import isa.apoteka.domain.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysAdminRepository  extends JpaRepository<SystemAdmin, Long> {
}

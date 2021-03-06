package isa.apoteka.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import isa.apoteka.domain.Notification;

public interface NotificationReposiotry extends JpaRepository<Notification, Long> {

	@Modifying
	@Query(value = "insert into notification (date, user_id, message) VALUES (:date,:userId,:message)", nativeQuery = true)
	void addMessage(Date date, Long userId, String message);

	@Query("from Notification n join n.user u where u.id=:id")
	List<Notification> findAllByUser(Long id);
}

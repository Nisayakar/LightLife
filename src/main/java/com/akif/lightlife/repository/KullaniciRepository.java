package com.akif.lightlife.repository;



import com.akif.lightlife.entity.Kullanici;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KullaniciRepository extends JpaRepository<Kullanici, Long> {
	Optional<Kullanici> findByEmail(String email);
	
	boolean existsByEmail(String email);
}
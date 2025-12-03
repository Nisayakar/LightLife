package com.akif.lightlife.repository;

import com.akif.lightlife.entity.Diyetisyen;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiyetisyenRepository extends JpaRepository<Diyetisyen, Long> {
	Optional<Diyetisyen> findByEmail(String email);
	
	boolean existsByEmail(String email);
}
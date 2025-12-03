package com.akif.lightlife.repository;

import com.akif.lightlife.entity.Egzersiz;
import com.akif.lightlife.entity.Kullanici;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EgzersizRepository extends JpaRepository<Egzersiz, Long> { 
	
	List<Egzersiz> findByKullanici(Kullanici kullanici);
	
	List<Egzersiz> findByKullaniciAndTarih(Kullanici kullanici , LocalDate tarih);
}
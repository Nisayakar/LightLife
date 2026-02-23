package com.akif.lightlife.repository;

import com.akif.lightlife.entity.Egzersiz;
import com.akif.lightlife.entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EgzersizRepository extends JpaRepository<Egzersiz, Long> {

    List<Egzersiz> findByKullanici(Kullanici kullanici);

    List<Egzersiz> findByKullaniciAndTarih(Kullanici kullanici, LocalDate tarih);

    List<Egzersiz> findByKullanici_IdAndTarih(Long kullaniciId, LocalDate tarih);

    List<Egzersiz> findByKullanici_IdAndTarihBetween(Long kullaniciId, LocalDate start, LocalDate end);
}

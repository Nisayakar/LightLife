package com.akif.lightlife.repository;

import com.akif.lightlife.entity.Kilo;
import com.akif.lightlife.entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface KiloRepository extends JpaRepository<Kilo, Long> {


    List<Kilo> findByKullanici(Kullanici kullanici);


    Optional<Kilo> findByKullaniciAndTarih(Kullanici kullanici, LocalDate tarih);

  
    Optional<Kilo> findByKullanici_IdAndTarih(Long kullaniciId, LocalDate tarih);

    List<Kilo> findByKullanici_Id(Long kullaniciId);

    List<Kilo> findByKullanici_IdAndTarihBetween(Long kullaniciId, LocalDate start, LocalDate end);


    Optional<Kilo> findTopByKullanici_IdOrderByTarihDesc(Long kullaniciId);
}

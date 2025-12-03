package com.akif.lightlife.repository;

import com.akif.lightlife.entity.Kilo;
import com.akif.lightlife.entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface KiloRepository extends JpaRepository<Kilo, Long> {

    List<Kilo> findByKullanici(Kullanici kullanici);

    List<Kilo> findByKullaniciAndTarih(Kullanici kullanici, LocalDate tarih);
}

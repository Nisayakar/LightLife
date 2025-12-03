package com.akif.lightlife.repository;

import com.akif.lightlife.entity.Kullanici;
import com.akif.lightlife.entity.Ogun;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OgunRepository extends JpaRepository<Ogun, Long> {

    List<Ogun> findByKullaniciAndTarih(Kullanici kullanici, LocalDate tarih);
}

package com.akif.lightlife.repository;

import com.akif.lightlife.entity.Aktivite;
import com.akif.lightlife.entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AktiviteRepository extends JpaRepository<Aktivite, Long> {

    List<Aktivite> findByKullaniciAndTarih(Kullanici kullanici, LocalDate tarih);

    List<Aktivite> findByKullanici(Kullanici kullanici);
}

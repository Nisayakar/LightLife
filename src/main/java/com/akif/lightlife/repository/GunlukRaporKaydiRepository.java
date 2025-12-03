package com.akif.lightlife.repository;

import com.akif.lightlife.entity.GunlukRaporKaydi;
import com.akif.lightlife.entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface GunlukRaporKaydiRepository extends JpaRepository<GunlukRaporKaydi, Long> {

    List<GunlukRaporKaydi> findByKullanici(Kullanici kullanici);

    GunlukRaporKaydi findByKullaniciAndTarih(Kullanici kullanici, LocalDate tarih);
}

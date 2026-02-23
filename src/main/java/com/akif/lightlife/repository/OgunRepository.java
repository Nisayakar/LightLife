package com.akif.lightlife.repository;

import com.akif.lightlife.entity.Ogun;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OgunRepository extends JpaRepository<Ogun, Long> {

    List<Ogun> findByKullanici_Id(Long kullaniciId);

    List<Ogun> findByKullanici_IdAndTarih(Long kullaniciId, LocalDate tarih);

    List<Ogun> findByKullaniciIdAndTarih(Long kullaniciId, LocalDate tarih);

    List<Ogun> findByKullanici_IdAndTarihBetween(Long kullaniciId, LocalDate start, LocalDate end);
}

package com.akif.lightlife.repository;

import com.akif.lightlife.entity.Mesaj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MesajRepository extends JpaRepository<Mesaj, Long> {

    @Query("""
        SELECT m FROM Mesaj m 
        WHERE (m.gonderenId = :kullaniciId AND m.aliciId = :diyetisyenId)
           OR (m.gonderenId = :diyetisyenId AND m.aliciId = :kullaniciId)
        ORDER BY m.tarih ASC
        """)
    List<Mesaj> sohbetGetir(Long kullaniciId, Long diyetisyenId);
}

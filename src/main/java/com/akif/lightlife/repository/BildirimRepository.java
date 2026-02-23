package com.akif.lightlife.repository;

import com.akif.lightlife.entity.Bildirim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BildirimRepository extends JpaRepository<Bildirim, Long> {

    List<Bildirim> findByKullaniciId(Long kullaniciId);

    List<Bildirim> findByKullaniciIdAndOkunduMuFalse(Long kullaniciId);
    @Modifying
    @Query("UPDATE Bildirim b SET b.okunduMu = true WHERE b.kullaniciId = :kullaniciId AND b.okunduMu = false")
    int tumunuOkunduYap(@Param("kullaniciId") Long kullaniciId);

}

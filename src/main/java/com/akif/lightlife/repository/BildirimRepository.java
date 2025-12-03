package com.akif.lightlife.repository;

import com.akif.lightlife.entity.Bildirim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BildirimRepository extends JpaRepository<Bildirim, Long> {

    List<Bildirim> findByKullaniciId(Long kullaniciId);

    List<Bildirim> findByKullaniciIdAndOkunduMuFalse(Long kullaniciId);
}

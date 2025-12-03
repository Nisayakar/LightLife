package com.akif.lightlife.repository;

import com.akif.lightlife.entity.Egitim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EgitimRepository extends JpaRepository<Egitim, Long> {

    List<Egitim> findByKategori(String kategori);

    List<Egitim> findByDiyetisyenId(Long diyetisyenId);
}

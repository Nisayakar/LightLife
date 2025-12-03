package com.akif.lightlife.repository;

import com.akif.lightlife.entity.Kampanya;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KampanyaRepository extends JpaRepository<Kampanya, Long> {

    List<Kampanya> findByDiyetisyenId(Long diyetisyenId);

    List<Kampanya> findByKategori(String kategori);

    List<Kampanya> findByAktifMiTrue();

}

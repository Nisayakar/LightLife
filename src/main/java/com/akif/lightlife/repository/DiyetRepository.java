package com.akif.lightlife.repository;

import com.akif.lightlife.entity.Diyet;
import com.akif.lightlife.entity.Diyetisyen;
import com.akif.lightlife.entity.Kullanici;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiyetRepository extends JpaRepository<Diyet, Long> {


    boolean existsByDiyetAdi(String diyetAdi);


    List<Diyet> findByKullaniciAndAktifMiTrue(Kullanici kullanici);


    List<Diyet> findByDiyetisyen(Diyetisyen diyetisyen);
}

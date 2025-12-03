package com.akif.lightlife.repository;

import com.akif.lightlife.entity.DestekTalebi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DestekTalebiRepository extends JpaRepository<DestekTalebi, Long> {

    List<DestekTalebi> findByKullaniciId(Long kullaniciId);
}

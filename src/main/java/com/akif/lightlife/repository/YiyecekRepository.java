package com.akif.lightlife.repository;

import com.akif.lightlife.entity.Yiyecek;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YiyecekRepository extends JpaRepository<Yiyecek, Long> {

    boolean existsByAd(String ad);
}

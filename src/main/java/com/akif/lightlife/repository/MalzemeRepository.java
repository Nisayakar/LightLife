package com.akif.lightlife.repository;

import com.akif.lightlife.entity.DiyetTarifi;
import com.akif.lightlife.entity.Malzeme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MalzemeRepository extends JpaRepository<Malzeme, Long> {

    List<Malzeme> findByTarif(DiyetTarifi tarif);
}

package com.akif.lightlife.repository;

import com.akif.lightlife.entity.Ogun;
import com.akif.lightlife.entity.OgunDiyetTarifi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OgunDiyetTarifiRepository extends JpaRepository<OgunDiyetTarifi, Long> {

    List<OgunDiyetTarifi> findByOgun(Ogun ogun);
}

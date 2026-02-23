package com.akif.lightlife.repository;

import com.akif.lightlife.entity.DiyetTarifi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiyetTarifiRepository extends JpaRepository<DiyetTarifi, Long> {


    List<DiyetTarifi> findTop10ByAdContainingIgnoreCaseOrderByAdAsc(String ad);
}

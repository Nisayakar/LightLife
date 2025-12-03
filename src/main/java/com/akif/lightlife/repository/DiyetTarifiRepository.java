package com.akif.lightlife.repository;

import com.akif.lightlife.entity.DiyetTarifi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiyetTarifiRepository extends JpaRepository<DiyetTarifi, Long> { }
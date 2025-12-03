package com.akif.lightlife.repository;

import com.akif.lightlife.entity.Odeme;
import com.akif.lightlife.entity.Abonelik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OdemeRepository extends JpaRepository<Odeme, Long> {

    List<Odeme> findByAbonelik(Abonelik abonelik);
}

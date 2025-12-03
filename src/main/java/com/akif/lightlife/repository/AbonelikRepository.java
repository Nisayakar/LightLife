package com.akif.lightlife.repository;

import com.akif.lightlife.entity.Abonelik;
import com.akif.lightlife.entity.Kullanici;
import com.akif.lightlife.entity.Diyetisyen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbonelikRepository extends JpaRepository<Abonelik, Long> {

    List<Abonelik> findByKullanici(Kullanici kullanici);

    List<Abonelik> findByDiyetisyen(Diyetisyen diyetisyen);

    List<Abonelik> findByKullaniciAndAktifMiTrue(Kullanici kullanici);
}

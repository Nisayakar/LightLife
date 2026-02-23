package com.akif.lightlife.repository;

import com.akif.lightlife.entity.Kullanici;
import com.akif.lightlife.entity.KullaniciDiyetisyen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KullaniciDiyetisyenRepository extends JpaRepository<KullaniciDiyetisyen, Long> {

    List<KullaniciDiyetisyen> findByDiyetisyenIdAndBitisTarihiIsNull(Long diyetisyenId);

    List<KullaniciDiyetisyen> findByKullaniciIdAndBitisTarihiIsNull(Long kullaniciId);

    Optional<KullaniciDiyetisyen> findFirstByKullaniciIdAndBitisTarihiIsNull(Long kullaniciId);

    boolean existsByKullaniciIdAndDiyetisyenIdAndBitisTarihiIsNull(Long kullaniciId, Long diyetisyenId);

	KullaniciDiyetisyen findByKullaniciAndAktifTrue(Kullanici kullanici);
}

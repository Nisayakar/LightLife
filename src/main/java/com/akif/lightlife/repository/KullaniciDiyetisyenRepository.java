package com.akif.lightlife.repository;

import com.akif.lightlife.entity.Diyetisyen;
import com.akif.lightlife.entity.Kullanici;
import com.akif.lightlife.entity.KullaniciDiyetisyen;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KullaniciDiyetisyenRepository extends JpaRepository<KullaniciDiyetisyen, Long> {
	List<KullaniciDiyetisyen> findByKullanici(Kullanici kullanici);
	
	List<KullaniciDiyetisyen> findByDiyetisyen(Diyetisyen diyetisyen);
	
	KullaniciDiyetisyen findByKullaniciAndAktifTrue(Kullanici kullanici);
	
}
package com.akif.lightlife.service;

import com.akif.lightlife.entity.KullaniciDiyetisyen;

public interface KullanıcıDiyetisyenService {
	
	KullaniciDiyetisyen ata(Long kullaniciId , Long diyetisyenId);
	
	KullaniciDiyetisyen aktifDiyetisyen(Long kullaniciId);
	
	void diyetisyenDegistir(Long kullaniciId , Long yeniDiyetisyenId);
}

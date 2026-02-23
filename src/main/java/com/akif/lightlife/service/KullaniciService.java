package com.akif.lightlife.service;

import com.akif.lightlife.dto.request.KullaniciGirisRequest;
import com.akif.lightlife.dto.request.KullaniciKayitRequest;
import com.akif.lightlife.dto.request.KullaniciProfilGuncelleRequest;
import com.akif.lightlife.dto.response.KullaniciResponse;

import java.util.List;

public interface KullaniciService {

 
    KullaniciResponse kayit(KullaniciKayitRequest request);


    KullaniciResponse giris(KullaniciGirisRequest request);

   
    List<KullaniciResponse> getAll();


    KullaniciResponse getById(Long id);

    
    KullaniciResponse profilGuncelle(Long id, KullaniciProfilGuncelleRequest request);

 
    void sifreGuncelle(Long id, String eskiSifre, String yeniSifre);

   
    void diyetisyenAta(Long kullaniciId, Long diyetisyenId);
}

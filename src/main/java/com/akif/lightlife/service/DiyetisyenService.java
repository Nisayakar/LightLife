package com.akif.lightlife.service;

import com.akif.lightlife.dto.request.DiyetisyenGirisRequest;
import com.akif.lightlife.dto.request.DiyetisyenKayitRequest;
import com.akif.lightlife.dto.request.DiyetisyenProfilGuncelleRequest;
import com.akif.lightlife.dto.response.DiyetisyenResponse;
import com.akif.lightlife.dto.response.KullaniciResponse;

import java.util.List;

public interface DiyetisyenService {

    DiyetisyenResponse kayit(DiyetisyenKayitRequest request);

    DiyetisyenResponse giris(DiyetisyenGirisRequest request);

    DiyetisyenResponse getById(Long id);

    List<DiyetisyenResponse> getAll();

 
    DiyetisyenResponse profilGuncelle(Long id, DiyetisyenProfilGuncelleRequest request);


    List<KullaniciResponse> getKullanicilar(Long diyetisyenId);
    
    void sifreDegistir(Long id, String eskiSifre, String yeniSifre);
    
    

}

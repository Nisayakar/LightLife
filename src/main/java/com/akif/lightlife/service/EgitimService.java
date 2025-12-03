package com.akif.lightlife.service;

import com.akif.lightlife.dto.request.EgitimOlusturRequest;
import com.akif.lightlife.dto.response.EgitimResponse;

import java.util.List;

public interface EgitimService {

    EgitimResponse olustur(EgitimOlusturRequest request);

    List<EgitimResponse> tumEgitimler();

    List<EgitimResponse> kategoriyeGore(String kategori);

    List<EgitimResponse> diyetisyenEgitimleri(Long diyetisyenId);

    EgitimResponse egitimGetir(Long id);

    EgitimResponse goruntule(Long id); 
}

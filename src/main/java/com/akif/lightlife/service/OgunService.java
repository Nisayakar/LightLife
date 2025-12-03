package com.akif.lightlife.service;

import com.akif.lightlife.dto.request.OgunKayitRequest;
import com.akif.lightlife.dto.request.OgunTarifEkleRequest;
import com.akif.lightlife.dto.response.OgunResponse;

import java.util.List;

public interface OgunService {

    OgunResponse ogunEkle(OgunKayitRequest r);

    OgunResponse oguneTarifEkle(OgunTarifEkleRequest r);

    OgunResponse ogunGetir(Long id);

    List<OgunResponse> kullanicininGunlukOgunleri(Long kullaniciId);
}

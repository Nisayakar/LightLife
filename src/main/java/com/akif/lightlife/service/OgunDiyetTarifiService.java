package com.akif.lightlife.service;

import java.util.List;

import com.akif.lightlife.dto.request.OgunTarifEkleRequest;
import com.akif.lightlife.dto.response.OgunTarifResponse;

public interface OgunDiyetTarifiService {

    OgunTarifResponse ekle(OgunTarifEkleRequest r);

    List<OgunTarifResponse> ogunTarifleri(Long ogunId);

    void sil(Long id);
}

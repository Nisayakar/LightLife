package com.akif.lightlife.service;

import com.akif.lightlife.dto.request.OgunDiyetTarifiEkleRequest;
import com.akif.lightlife.dto.response.OgunDiyetTarifiResponse;

import java.util.List;

public interface OgunDiyetTarifiService {

    OgunDiyetTarifiResponse tarifEkle(OgunDiyetTarifiEkleRequest request);

   
    List<OgunDiyetTarifiResponse> ogunTarifleri(Long ogunId);
}

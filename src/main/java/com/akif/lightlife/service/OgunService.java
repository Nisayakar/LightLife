package com.akif.lightlife.service;

import com.akif.lightlife.dto.request.OgunEkleRequest;
import com.akif.lightlife.dto.response.OgunResponse;

import java.util.List;

public interface OgunService {

    OgunResponse ogunEkle(OgunEkleRequest request);

  
    List<OgunResponse> bugunOgunleri(Long kullaniciId);
}

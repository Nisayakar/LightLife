package com.akif.lightlife.service;

import com.akif.lightlife.dto.request.DiyetOlusturRequest;
import com.akif.lightlife.dto.response.DiyetResponse;

import java.util.List;

public interface DiyetService {

    DiyetResponse olustur(DiyetOlusturRequest request);

    List<DiyetResponse> kullaniciAktifDiyetleri(Long kullaniciId);

    DiyetResponse diyetBitir(Long diyetId);
}

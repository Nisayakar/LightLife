package com.akif.lightlife.service;

import com.akif.lightlife.dto.request.AktiviteEkleRequest;
import com.akif.lightlife.dto.response.AktiviteResponse;

import java.util.List;

public interface AktiviteService {

    AktiviteResponse ekle(AktiviteEkleRequest request);

    List<AktiviteResponse> kullaniciGunlukAktivite(Long kullaniciId);

    List<AktiviteResponse> kullaniciTumAktiviteler(Long kullaniciId);
}

package com.akif.lightlife.service;

import com.akif.lightlife.dto.request.KiloKayitRequest;
import com.akif.lightlife.dto.response.KiloResponse;

import java.util.List;

public interface KiloService {

    KiloResponse ekle(KiloKayitRequest request);

    List<KiloResponse> kullaniciKilolari(Long kullaniciId);

    KiloResponse bugunKilo(Long kullaniciId);
}

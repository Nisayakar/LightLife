package com.akif.lightlife.service;

import com.akif.lightlife.dto.request.DestekTalebiOlusturRequest;
import com.akif.lightlife.dto.response.DestekTalebiResponse;

import java.util.List;

public interface DestekTalebiService {

    DestekTalebiResponse talepOlustur(DestekTalebiOlusturRequest request);

    List<DestekTalebiResponse> kullaniciTalepleri(Long kullaniciId);

    DestekTalebiResponse talepDurumDegistir(Long talepId, String yeniDurum);
}

package com.akif.lightlife.service;

import com.akif.lightlife.dto.request.AbonelikOlusturRequest;
import com.akif.lightlife.dto.response.AbonelikResponse;

import java.util.List;

public interface AbonelikService {

    AbonelikResponse abonelikOlustur(AbonelikOlusturRequest request);

    List<AbonelikResponse> kullaniciAbonelikleri(Long kullaniciId);

    AbonelikResponse abonelikIptal(Long abonelikId);
}

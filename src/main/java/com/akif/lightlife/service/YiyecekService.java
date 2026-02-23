package com.akif.lightlife.service;

import com.akif.lightlife.dto.request.YiyecekKayitRequest;
import com.akif.lightlife.dto.request.YiyecekGuncelleRequest;
import com.akif.lightlife.dto.response.YiyecekResponse;

import java.util.List;

public interface YiyecekService {

    YiyecekResponse ekle(YiyecekKayitRequest request);

    YiyecekResponse guncelle(Long id, YiyecekGuncelleRequest request);

    void sil(Long id);

    YiyecekResponse getir(Long id);

    List<YiyecekResponse> listele();
    List<YiyecekResponse> getAll();
}

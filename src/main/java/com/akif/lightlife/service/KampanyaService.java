package com.akif.lightlife.service;

import com.akif.lightlife.dto.request.KampanyaOlusturRequest;
import com.akif.lightlife.dto.response.KampanyaResponse;

import java.util.List;

public interface KampanyaService {

    KampanyaResponse olustur(KampanyaOlusturRequest request);

    List<KampanyaResponse> tumKampanyalar();


    List<KampanyaResponse> aktifKampanyalar();


    List<KampanyaResponse> kategoriyeGore(String kategori);

    List<KampanyaResponse> diyetisyenKampanyalari(Long diyetisyenId);

    KampanyaResponse kampanyaGetir(Long id);

    KampanyaResponse durumDegistir(Long id, boolean aktifMi);

    void sil(Long id);


    double indirimliTutarHesapla(Long kampanyaId, double tutar);
}

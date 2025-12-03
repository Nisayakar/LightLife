package com.akif.lightlife.mapper;

import com.akif.lightlife.dto.response.KampanyaResponse;
import com.akif.lightlife.entity.Kampanya;
import org.springframework.stereotype.Component;

@Component
public class KampanyaMapper {

    public KampanyaResponse toResponse(Kampanya k) {
        return KampanyaResponse.builder()
                .id(k.getId())
                .diyetisyenId(k.getDiyetisyenId())
                .baslik(k.getBaslik())
                .aciklama(k.getAciklama())
                .indirimYuzdesi(k.getIndirimYuzdesi())
                .baslangicTarihi(k.getBaslangicTarihi().toString())
                .bitisTarihi(k.getBitisTarihi().toString())
                .aktifMi(k.isAktifMi())
                .kategori(k.getKategori())
                .build();
    }
}

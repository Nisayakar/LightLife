package com.akif.lightlife.mapper;

import com.akif.lightlife.dto.response.EgitimResponse;
import com.akif.lightlife.entity.Egitim;
import org.springframework.stereotype.Component;

@Component
public class EgitimMapper {

    public EgitimResponse toResponse(Egitim e) {
        return EgitimResponse.builder()
                .id(e.getId())
                .diyetisyenId(e.getDiyetisyenId())
                .baslik(e.getBaslik())
                .icerik(e.getIcerik())
                .kategori(e.getKategori())
                .tarih(e.getTarih().toString())
                .goruntulenmeSayisi(e.getGoruntulenmeSayisi())
                .build();
    }
}

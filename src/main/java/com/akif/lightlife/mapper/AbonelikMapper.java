package com.akif.lightlife.mapper;

import com.akif.lightlife.dto.response.AbonelikResponse;
import com.akif.lightlife.entity.Abonelik;
import org.springframework.stereotype.Component;

@Component
public class AbonelikMapper {

    public AbonelikResponse toResponse(Abonelik a) {
        return AbonelikResponse.builder()
                .id(a.getId())
                .kullaniciId(a.getKullanici().getId())
                .diyetisyenId(a.getDiyetisyen().getId())
                .planAdi(a.getPlanAdi())
                .sureAy(a.getSureAy())
                .aylikUcret(a.getAylikUcret())
                .baslangicTarihi(a.getBaslangicTarihi() != null ? a.getBaslangicTarihi().toString() : null)
                .bitisTarihi(a.getBitisTarihi() != null ? a.getBitisTarihi().toString() : null)
                .aktifMi(a.isAktifMi())
                .build();
    }
}

package com.akif.lightlife.mapper;

import com.akif.lightlife.dto.response.DiyetResponse;
import com.akif.lightlife.entity.Diyet;
import org.springframework.stereotype.Component;

@Component
public class DiyetMapper {

    public DiyetResponse toResponse(Diyet d) {
        return DiyetResponse.builder()
                .id(d.getId())
                .kullaniciId(d.getKullanici().getId())
                .diyetisyenId(d.getDiyetisyen().getId())
                .diyetAdi(d.getDiyetAdi())
                .gunlukKaloriHedefi(d.getGunlukKaloriHedefi())
                .karbonhidratHedefi(d.getKarbonhidratHedefi())
                .proteinHedefi(d.getProteinHedefi())
                .yagHedefi(d.getYagHedefi())
                .sabah(d.getSabah())
                .ogle(d.getOgle())
                .aksam(d.getAksam())
                .baslangicTarihi(d.getBaslangicTarihi().toString())
                .bitisTarihi(d.getBitisTarihi() != null ? d.getBitisTarihi().toString() : null)
                .aktifMi(d.isAktifMi())
                .build();
    }
}

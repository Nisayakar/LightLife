package com.akif.lightlife.mapper;

import com.akif.lightlife.dto.response.DiyetisyenResponse;
import com.akif.lightlife.entity.Diyetisyen;
import org.springframework.stereotype.Component;

@Component
public class DiyetisyenMapper {

    public DiyetisyenResponse toResponse(Diyetisyen d) {
        return DiyetisyenResponse.builder()
                .id(d.getId())
                .ad(d.getAd())
                .soyad(d.getSoyad())
                .email(d.getEmail())
                .telefon(d.getTelefon())
                .uzmanlik(d.getUzmanlik())
                .deneyimYili(d.getDeneyimYili())
                .build();
    }
}

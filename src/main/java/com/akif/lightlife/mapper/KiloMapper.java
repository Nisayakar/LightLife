package com.akif.lightlife.mapper;

import com.akif.lightlife.entity.Kilo;
import com.akif.lightlife.dto.response.KiloResponse;
import org.springframework.stereotype.Component;

@Component
public class KiloMapper {

    public KiloResponse toResponse(Kilo k) {
        return KiloResponse.builder()
                .id(k.getId())
                .kiloDeger(k.getKiloDeger())
                .tarih(k.getTarih().toString())
                .kullaniciId(k.getKullanici().getId())
                .build();
    }
}

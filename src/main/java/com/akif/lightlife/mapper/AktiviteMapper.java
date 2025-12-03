package com.akif.lightlife.mapper;

import com.akif.lightlife.dto.response.AktiviteResponse;
import com.akif.lightlife.entity.Aktivite;
import org.springframework.stereotype.Component;

@Component
public class AktiviteMapper {

    public AktiviteResponse toResponse(Aktivite a) {
        return AktiviteResponse.builder()
                .id(a.getId())
                .adimSayisi(a.getAdimSayisi())
                .mesafeKm(a.getMesafeKm())
                .sureDakika(a.getSureDakika())
                .yakilanKalori(a.getYakilanKalori())
                .tarih(a.getTarih().toString())
                .build();
    }
}

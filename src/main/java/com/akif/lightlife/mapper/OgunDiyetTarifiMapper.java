package com.akif.lightlife.mapper;

import org.springframework.stereotype.Component;

import com.akif.lightlife.dto.response.OgunTarifResponse;
import com.akif.lightlife.entity.OgunDiyetTarifi;

@Component
public class OgunDiyetTarifiMapper {

    public OgunTarifResponse toResponse(OgunDiyetTarifi o) {

        int toplamKalori = o.getPorsiyon() * o.getTarif().getToplamKalori();

        return OgunTarifResponse.builder()
                .id(o.getId())
                .tarifId(o.getTarif().getId())
                .tarifAd(o.getTarif().getAd())
                .porsiyon(o.getPorsiyon())
                .toplamKalori(toplamKalori)
                .build();
    }
}

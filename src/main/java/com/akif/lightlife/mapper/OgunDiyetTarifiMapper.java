package com.akif.lightlife.mapper;

import com.akif.lightlife.dto.response.OgunDiyetTarifiResponse;
import com.akif.lightlife.entity.DiyetTarifi;
import com.akif.lightlife.entity.OgunDiyetTarifi;
import org.springframework.stereotype.Component;

@Component
public class OgunDiyetTarifiMapper {

    public OgunDiyetTarifiResponse toResponse(OgunDiyetTarifi t) {

        Long ogunId = t.getOgun() != null ? t.getOgun().getId() : null;
        DiyetTarifi tarif = t.getTarif();
        Long tarifId = tarif != null ? tarif.getId() : null;
        String tarifAdi = tarif != null ? tarif.getAd() : null;

        Integer tarifKalori = null;
        if (tarif != null) {
            // DiyetTarifi.toplamKalori muhtemelen "int"
            // null olamayacağı için sadece tarif null değilse çarpıyoruz
            tarifKalori = tarif.getToplamKalori() * t.getPorsiyon();
        }

        return OgunDiyetTarifiResponse.builder()
                .id(t.getId())
                .ogunId(ogunId)
                .tarifId(tarifId)
                .porsiyon(t.getPorsiyon())
                .tarifAdi(tarifAdi)
                .tarifKalori(tarifKalori)
                .build();
    }
}

package com.akif.lightlife.mapper;

import com.akif.lightlife.dto.response.YiyecekResponse;
import com.akif.lightlife.entity.Yiyecek;
import org.springframework.stereotype.Component;

@Component
public class YiyecekMapper {

    public YiyecekResponse toResponse(Yiyecek y) {
        return YiyecekResponse.builder()
                .id(y.getId())
                .ad(y.getAd())
                .kalori(y.getKalori())
                .karbonhidrat(y.getKarbonhidrat())
                .protein(y.getProtein())
                .yag(y.getYag())
                .build();
    }
}

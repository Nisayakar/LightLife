package com.akif.lightlife.mapper;

import com.akif.lightlife.dto.response.MalzemeResponse;
import com.akif.lightlife.entity.KaloriHesaplayici;
import com.akif.lightlife.entity.Malzeme;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MalzemeMapper {

    private final KaloriHesaplayici kaloriHesaplayici;

    public MalzemeResponse toResponse(Malzeme m) {
        if (m == null) {
            return null;
        }

        int kalori = kaloriHesaplayici.malzemeKalori(m);

        String yiyecekAd = null;
        if (m.getYiyecek() != null) {
            yiyecekAd = m.getYiyecek().getAd();
        }

        return MalzemeResponse.builder()
                .id(m.getId())
                .yiyecekAd(yiyecekAd)
                .gram(m.getGram())
                .kalori(kalori)
                .build();
    }
}

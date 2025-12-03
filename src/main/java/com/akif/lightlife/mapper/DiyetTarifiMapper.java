package com.akif.lightlife.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.akif.lightlife.dto.response.MalzemeResponse;
import com.akif.lightlife.dto.response.TarifResponse;
import com.akif.lightlife.entity.DiyetTarifi;
import com.akif.lightlife.entity.Malzeme;

@Component
public class DiyetTarifiMapper {

    public TarifResponse toResponse(DiyetTarifi t) {

        return TarifResponse.builder()
                .id(t.getId())
                .ad(t.getAd())
                .aciklama(t.getAciklama())
                .toplamKalori(t.getToplamKalori())
                .diyetisyenId(t.getDiyetisyen().getId())
                .malzemeler(
                        t.getMalzemeler().stream()
                                .map(this::mapMalzeme)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private MalzemeResponse mapMalzeme(Malzeme m) {

        int kalori = (int) ((m.getYiyecek().getKalori() / 100.0) * m.getGram());

        return MalzemeResponse.builder()
                .id(m.getId())
                .yiyecekAd(m.getYiyecek().getAd())  // ← ARTIK HATASIZ
                .gram(m.getGram())
                .kalori(kalori)
                .build();
    }
}

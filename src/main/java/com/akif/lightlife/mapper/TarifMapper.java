package com.akif.lightlife.mapper;

import com.akif.lightlife.dto.response.MalzemeResponse;
import com.akif.lightlife.dto.response.TarifResponse;
import com.akif.lightlife.entity.DiyetTarifi;
import com.akif.lightlife.entity.KaloriHesaplayici;
import com.akif.lightlife.entity.Malzeme;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TarifMapper {

    private final MalzemeMapper malzemeMapper;
    private final KaloriHesaplayici kaloriHesaplayici;

    public TarifResponse toResponse(DiyetTarifi t) {
        if (t == null) {
            return null;
        }

     
        List<MalzemeResponse> malzemeList = new ArrayList<>();

        if (t.getMalzemeler() != null) {
            for (Malzeme m : t.getMalzemeler()) {
                MalzemeResponse mr = malzemeMapper.toResponse(m);
                if (mr != null) {
                    malzemeList.add(mr);
                }
            }
        }

        int toplamKalori = kaloriHesaplayici.tarifToplamKalori(t);

        return TarifResponse.builder()
                .id(t.getId())
                .ad(t.getAd())
                .aciklama(t.getAciklama())
                .toplamKalori(toplamKalori)
                .diyetisyenId(
                        t.getDiyetisyen() != null
                                ? t.getDiyetisyen().getId()
                                : null
                )
                .malzemeler(malzemeList) 
                .build();
    }
}

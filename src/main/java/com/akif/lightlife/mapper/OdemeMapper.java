package com.akif.lightlife.mapper;

import com.akif.lightlife.dto.response.OdemeResponse;
import com.akif.lightlife.entity.Odeme;
import org.springframework.stereotype.Component;

@Component
public class OdemeMapper {

    public OdemeResponse toResponse(Odeme o) {
        return OdemeResponse.builder()
                .id(o.getId())
                .abonelikId(o.getAbonelik().getId())
                .tutar(o.getTutar())
                .odemeYontemi(o.getOdemeYontemi())
                .durum(o.getDurum().name())
                .tarih(o.getTarih().toString())
                .islemNo(o.getIslemNo())
                .build();
    }
}

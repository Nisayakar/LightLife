package com.akif.lightlife.mapper;

import com.akif.lightlife.dto.response.MesajResponse;
import com.akif.lightlife.entity.Mesaj;
import org.springframework.stereotype.Component;

@Component
public class MesajMapper {

    public MesajResponse toResponse(Mesaj m) {
        return MesajResponse.builder()
                .id(m.getId())
                .gonderenId(m.getGonderenId())
                .aliciId(m.getAliciId())
                .icerik(m.getIcerik())
                .tarih(m.getTarih())
                .okunduMu(m.isOkunduMu())
                .build();
    }
}

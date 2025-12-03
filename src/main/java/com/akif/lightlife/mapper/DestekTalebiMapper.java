package com.akif.lightlife.mapper;

import com.akif.lightlife.dto.response.DestekTalebiResponse;
import com.akif.lightlife.entity.DestekTalebi;
import org.springframework.stereotype.Component;

@Component
public class DestekTalebiMapper {

    public DestekTalebiResponse toResponse(DestekTalebi t) {
        return DestekTalebiResponse.builder()
                .id(t.getId())
                .kullaniciId(t.getKullaniciId())
                .konu(t.getKonu())
                .mesaj(t.getMesaj())
                .durum(t.getDurum().name())
                .tarih(t.getTarih().toString())
                .build();
    }
}

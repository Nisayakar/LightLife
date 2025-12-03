package com.akif.lightlife.mapper;

import org.springframework.stereotype.Component;

import com.akif.lightlife.dto.response.KullaniciResponse;
import com.akif.lightlife.entity.Kullanici;


@Component
public class KullaniciMapper {

    public KullaniciResponse toResponse(Kullanici k) {
        return KullaniciResponse.builder()
                .id(k.getId())
                .ad(k.getAd())
                .soyad(k.getSoyad())
                .email(k.getEmail())
                .telefon(k.getTelefon())
                .rol(k.getRol())
                .build();
    }
}
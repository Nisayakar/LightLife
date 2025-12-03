package com.akif.lightlife.mapper;

import com.akif.lightlife.dto.response.GunlukRaporKaydiResponse;
import com.akif.lightlife.entity.GunlukRaporKaydi;
import org.springframework.stereotype.Component;

@Component
public class GunlukRaporKaydiMapper {

    public GunlukRaporKaydiResponse toResponse(GunlukRaporKaydi g) {
        return GunlukRaporKaydiResponse.builder()
                .id(g.getId())
                .kullaniciId(g.getKullanici().getId())
                .tarih(g.getTarih().toString())
                .toplamKalori(g.getToplamKalori())
                .yakilanKalori(g.getYakilanKalori())
                .karbonhidrat(g.getKarbonhidrat())
                .protein(g.getProtein())
                .yag(g.getYag())
                .ogunSayisi(g.getOgunSayisi())
                .egzersizDakika(g.getEgzersizDakika())
                .kilo(g.getKilo())
                .build();
    }
}

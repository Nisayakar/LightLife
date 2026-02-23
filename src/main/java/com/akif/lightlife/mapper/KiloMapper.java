package com.akif.lightlife.mapper;

import org.springframework.stereotype.Component;

import com.akif.lightlife.dto.response.KiloResponse;
import com.akif.lightlife.entity.Kilo;
import com.akif.lightlife.util.BmiUtil;

@Component
public class KiloMapper {

    public KiloResponse toResponse(Kilo k) {
        Double bmi = null;
        String kategori = null;

        Double boyMetre = k.getKullanici().getBoyCm(); // boy alanın ismine göre değiştir
        if (boyMetre != null && boyMetre > 0) {
            bmi = BmiUtil.hesapla(k.getKiloDeger(), boyMetre);
            kategori = BmiUtil.kategori(bmi);
        }

        return KiloResponse.builder()
                .id(k.getId())
                .kiloDeger(k.getKiloDeger())
                .tarih(k.getTarih().toString())
                .kullaniciId(k.getKullanici().getId())
                .bmi(bmi)
                .bmiKategori(kategori)
                .build();
    }
}

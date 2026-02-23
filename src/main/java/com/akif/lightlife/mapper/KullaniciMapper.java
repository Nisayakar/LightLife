package com.akif.lightlife.mapper;

import com.akif.lightlife.dto.response.DiyetisyenResponse;
import com.akif.lightlife.dto.response.KullaniciResponse;
import com.akif.lightlife.entity.Diyetisyen;
import com.akif.lightlife.entity.Kullanici;
import org.springframework.stereotype.Component;

@Component
public class KullaniciMapper {

    public KullaniciResponse toResponse(Kullanici k) {
        if (k == null) return null;

        return KullaniciResponse.builder()
                .id(k.getId())
                .ad(k.getAd())
                .soyad(k.getSoyad())
                .email(k.getEmail())
                .telefon(k.getTelefon())
                .rol(k.getRol())
                .yas(k.getYas())
                .boyCm(k.getBoyCm())
                .kiloKg(k.getKiloKg())
                .hedefKilo(k.getHedefKilo())
                .cinsiyet(k.getCinsiyet())
                .saglikNotu(k.getSaglikNotu())

                // 🔥 Nested diyetisyen objesi
                .diyetisyen(toDiyetisyenResponse(k.getDiyetisyen()))

                // 🔥 Ek kolay kullanım alanları
                .diyetisyenId(k.getDiyetisyen() != null ? k.getDiyetisyen().getId() : null)
                .diyetisyenAd(k.getDiyetisyen() != null ? k.getDiyetisyen().getAd() : null)
                .diyetisyenSoyad(k.getDiyetisyen() != null ? k.getDiyetisyen().getSoyad() : null)

                .build();
    }

    private DiyetisyenResponse toDiyetisyenResponse(Diyetisyen d) {
        if (d == null) return null;

        return DiyetisyenResponse.builder()
                .id(d.getId())
                .ad(d.getAd())
                .soyad(d.getSoyad())
                .email(d.getEmail())      // 🔥 EKLENDİ
                .telefon(d.getTelefon())  // 🔥 EKLENDİ
                .uzmanlik(d.getUzmanlik())
                .deneyimYili(d.getDeneyimYili())
                .build();
    }
}

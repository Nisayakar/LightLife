package com.akif.lightlife.dto.response;

import com.akif.lightlife.enums.Cinsiyet;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KullaniciResponse {

    private Long id;

    private String ad;
    private String soyad;
    private String email;
    private String telefon;
    private String rol;

    private Integer yas;
    private Double boyCm;
    private Double kiloKg;
    private Double hedefKilo;
    private Cinsiyet cinsiyet;
    private String saglikNotu;

    // DİYETİSYEN (Nested Response - Mapper bunu dolduruyor)
    private DiyetisyenResponse diyetisyen;

    // FLAT alanlar backend tarafından doldurulsun istiyorsan:
    private Long diyetisyenId;
    private String diyetisyenAd;
    private String diyetisyenSoyad;
    private Integer hedefKalori;
}

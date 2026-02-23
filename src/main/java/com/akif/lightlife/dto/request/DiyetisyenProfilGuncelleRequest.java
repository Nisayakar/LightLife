package com.akif.lightlife.dto.request;

import lombok.Data;

@Data
public class DiyetisyenProfilGuncelleRequest {

    private String ad;
    private String soyad;
    private String telefon;
    private String uzmanlik;
    private Integer deneyimYili;
}

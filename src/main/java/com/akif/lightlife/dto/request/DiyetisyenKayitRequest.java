package com.akif.lightlife.dto.request;

import lombok.Data;

@Data
public class DiyetisyenKayitRequest {

    private String ad;
    private String soyad;
    private String email;
    private String telefon;
    private String sifre;
    private String uzmanlik;
    private Integer deneyimYili;
}

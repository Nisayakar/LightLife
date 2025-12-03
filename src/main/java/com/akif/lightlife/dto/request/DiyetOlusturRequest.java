package com.akif.lightlife.dto.request;

import lombok.Data;

@Data
public class DiyetOlusturRequest {

    private Long kullaniciId;
    private Long diyetisyenId;

    private String diyetAdi;

    private int gunlukKaloriHedefi;
    private int karbonhidratHedefi;
    private int proteinHedefi;
    private int yagHedefi;

    private String sabah;
    private String ogle;
    private String aksam;

    private String baslangicTarihi;
    private String bitisTarihi;
}

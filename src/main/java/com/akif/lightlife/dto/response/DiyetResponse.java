package com.akif.lightlife.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DiyetResponse {

    private Long id;
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

    private boolean aktifMi;
}

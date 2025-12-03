package com.akif.lightlife.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AbonelikResponse {

    private Long id;
    private Long kullaniciId;
    private Long diyetisyenId;
    private String planAdi;
    private int sureAy;
    private BigDecimal aylikUcret;
    private String baslangicTarihi;
    private String bitisTarihi;
    private boolean aktifMi;
}

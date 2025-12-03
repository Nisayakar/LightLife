package com.akif.lightlife.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AbonelikOlusturRequest {

    private Long kullaniciId;
    private Long diyetisyenId;
    private String planAdi;
    private int sureAy;
    private BigDecimal aylikUcret;
}

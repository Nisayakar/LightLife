package com.akif.lightlife.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OdemeResponse {

    private Long id;
    private Long abonelikId;
    private BigDecimal tutar;
    private String odemeYontemi;
    private String durum;
    private String tarih;
    private String islemNo;
}

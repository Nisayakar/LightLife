package com.akif.lightlife.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OdemeYapRequest {

    private Long abonelikId;
    private BigDecimal tutar;
    private String odemeYontemi; 
    private Long kampanyaId;
}

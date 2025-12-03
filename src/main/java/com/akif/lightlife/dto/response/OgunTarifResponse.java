package com.akif.lightlife.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OgunTarifResponse {

    private Long id;
    private Long tarifId;
    private String tarifAd;
    private int porsiyon;
    private int toplamKalori;
}

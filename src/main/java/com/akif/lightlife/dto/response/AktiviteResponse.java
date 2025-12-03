package com.akif.lightlife.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AktiviteResponse {

    private Long id;
    private int adimSayisi;
    private double mesafeKm;
    private int sureDakika;
    private int yakilanKalori;
    private String tarih;
}

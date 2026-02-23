package com.akif.lightlife.dto.request;

import lombok.Data;

@Data
public class YiyecekKayitRequest {

    private String ad;
    private Double kalori;
    private Double karbonhidrat;
    private Double protein;
    private Double yag;
    private Long diyetisyenId;
}

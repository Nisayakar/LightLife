package com.akif.lightlife.dto.request;

import lombok.Data;

@Data
public class YiyecekGuncelleRequest {

    private String ad;
    private Double kalori;
    private Double karbonhidrat;
    private Double protein;
    private Double yag;
}

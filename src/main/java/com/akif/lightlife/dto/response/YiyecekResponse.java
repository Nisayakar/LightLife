package com.akif.lightlife.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class YiyecekResponse {

    private Long id;
    private String ad;
    private int kalori;
    private double karbonhidrat;
    private double protein;
    private double yag;
}

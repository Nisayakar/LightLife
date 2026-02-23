package com.akif.lightlife.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class YiyecekResponse {

    private Long id;
    private String ad;
    private Double kalori;
    private Double karbonhidrat;
    private Double protein;
    private Double yag;
}

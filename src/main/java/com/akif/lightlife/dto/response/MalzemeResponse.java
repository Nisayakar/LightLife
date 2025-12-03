package com.akif.lightlife.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MalzemeResponse {

    private Long id;

    private String yiyecekAd;   

    private double gram;

    private int kalori;
}

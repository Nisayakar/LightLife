package com.akif.lightlife.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OgunDiyetTarifiResponse {

    private Long id;
    private Long ogunId;
    private Long tarifId;
    private int porsiyon;

    private String tarifAdi;
    private Integer tarifKalori; // toplam kalori = tarif.getToplamKalori() * porsiyon
}

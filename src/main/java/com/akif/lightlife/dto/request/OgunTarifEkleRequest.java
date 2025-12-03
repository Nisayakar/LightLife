package com.akif.lightlife.dto.request;

import lombok.Data;

@Data
public class OgunTarifEkleRequest {
    private Long ogunId;
    private Long tarifId;
    private int porsiyon;
}

package com.akif.lightlife.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KiloResponse {

    private Long id;
    private double kiloDeger;
    private String tarih;
    private Long kullaniciId;
}

package com.akif.lightlife.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class OgunResponse {

    private Long id;
    private String tip;
    private LocalDate tarih;
    private Long kullaniciId;

    private List<OgunTarifResponse> tarifler;
}

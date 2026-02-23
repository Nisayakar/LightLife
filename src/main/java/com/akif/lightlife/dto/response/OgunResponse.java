package com.akif.lightlife.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class OgunResponse {

    private Long id;
    private Long kullaniciId;
    private LocalDate tarih;
    private String tip;

    // Bu öğünün içindeki tüm tarifleri (porsiyon + tarif adı ile birlikte döner)
    private List<OgunDiyetTarifiResponse> tarifler;
}

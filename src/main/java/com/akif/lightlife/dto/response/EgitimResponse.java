package com.akif.lightlife.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EgitimResponse {

    private Long id;
    private Long diyetisyenId;
    private String baslik;
    private String icerik;
    private String kategori;
    private String tarih;
    private int goruntulenmeSayisi;
}

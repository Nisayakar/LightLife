package com.akif.lightlife.dto.request;

import lombok.Data;

@Data
public class EgitimOlusturRequest {

    private Long diyetisyenId;
    private String baslik;
    private String icerik;
    private String kategori;
}

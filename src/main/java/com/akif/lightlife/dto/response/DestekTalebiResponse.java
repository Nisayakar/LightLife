package com.akif.lightlife.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DestekTalebiResponse {

    private Long id;
    private Long kullaniciId;
    private String konu;
    private String mesaj;
    private String durum;
    private String tarih;
}

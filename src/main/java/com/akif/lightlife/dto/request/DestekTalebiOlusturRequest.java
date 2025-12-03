package com.akif.lightlife.dto.request;

import lombok.Data;

@Data
public class DestekTalebiOlusturRequest {

    private Long kullaniciId;
    private String konu;
    private String mesaj;
}

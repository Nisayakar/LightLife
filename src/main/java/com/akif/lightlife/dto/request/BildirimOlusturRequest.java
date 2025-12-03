package com.akif.lightlife.dto.request;

import lombok.Data;

@Data
public class BildirimOlusturRequest {

    private Long kullaniciId;
    private String baslik;
    private String mesaj;
    private String tip;
}

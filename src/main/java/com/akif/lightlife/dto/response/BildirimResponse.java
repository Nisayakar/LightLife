package com.akif.lightlife.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BildirimResponse {

    private Long id;
    private String baslik;
    private String mesaj;
    private String tip;
    private String tarih;
    private boolean okunduMu;
}

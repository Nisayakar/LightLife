package com.akif.lightlife.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BildirimResponse {
    private Long id;
    private String baslik;
    private String mesaj;
    private boolean okunduMu;
    private String tip;
    private String kaynak;   // 🔥 BUNU EKLE
    private LocalDateTime tarih;
}


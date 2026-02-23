package com.akif.lightlife.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {

    private LocalDateTime zaman;
    private String mesaj;
    private String kod;      // Örn: KULLANICI_BULUNAMADI
    private String path;     // İstek URL'i
}

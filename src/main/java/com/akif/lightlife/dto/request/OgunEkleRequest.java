package com.akif.lightlife.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OgunEkleRequest {

    private Long kullaniciId;

    /**
     * Boş gelirse servis tarafında bugün (LocalDate.now()) olarak ayarlanacak.
     */
    private LocalDate tarih;

    /**
     * Öğün tipi: SABAH / OGLE / AKSAM / ARA
     */
    private String tip;
    private Long tarifId;    // 🔥 Bu eksikti, eklendi
    private Integer porsiyon;
}

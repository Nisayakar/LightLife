package com.akif.lightlife.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KampanyaResponse {

    private Long id;
    private Long diyetisyenId;
    private String baslik;
    private String aciklama;
    private int indirimYuzdesi;
    private String baslangicTarihi;
    private String bitisTarihi;
    private boolean aktifMi;
    private String kategori;
}

package com.akif.lightlife.dto.request;

import lombok.Data;

@Data
public class KampanyaOlusturRequest {

    private Long diyetisyenId;
    private String baslik;
    private String aciklama;
    private int indirimYuzdesi;
    private String baslangicTarihi; 
    private String bitisTarihi;    
    private String kategori;
}

package com.akif.lightlife.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GunlukRaporKaydiResponse {

    private Long id;
    private Long kullaniciId;
    private String tarih;

    private int toplamKalori;
    private int yakilanKalori;

    private double karbonhidrat;
    private double protein;
    private double yag;

    private int ogunSayisi;
    private int egzersizDakika;

    private Double kilo;
}

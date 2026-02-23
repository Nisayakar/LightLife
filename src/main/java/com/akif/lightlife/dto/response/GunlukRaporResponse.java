package com.akif.lightlife.dto.response;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GunlukRaporResponse {

    // Bugün alınan toplam kalori
    private int toplamKalori;

    // Bugün yakılan toplam kalori (egzersiz + aktivite)
    private int yakilanKalori;

    // Makrolar (gram cinsinden)
    private double karbonhidrat;
    private double protein;
    private double yag;

    // Kaç öğün yedi
    private int ogunSayisi;

    // Bugün yapılan toplam egzersiz süresi (dakika)
    private int egzersizDakika;

    // Bugünkü kilo (yoksa en son ölçüm)
    private Double bugunKilo;

    // Öğünlerin özet listesi (örn: "SABAH - 450 kcal", "OGLE - 600 kcal")
    private List<String> ogunler;
    private Integer hedefKalori;
}

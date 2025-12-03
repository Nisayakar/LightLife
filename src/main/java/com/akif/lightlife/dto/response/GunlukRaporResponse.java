package com.akif.lightlife.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GunlukRaporResponse {

    private int toplamKalori;
    private int yakilanKalori;

    private double karbonhidrat;
    private double protein;
    private double yag;

    private int ogunSayisi;
    private int egzersizDakika;

    private Double bugunKilo;

    private List<String> ogunler;
}

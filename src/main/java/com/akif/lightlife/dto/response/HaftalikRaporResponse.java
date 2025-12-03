package com.akif.lightlife.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class HaftalikRaporResponse {

    private Map<String, Integer> gunlukKaloriler;
    private Map<String, Integer> gunlukYakilanKaloriler;
    private Map<String, Integer> gunlukOgunSayilari;

    private Double ilkKilo;
    private Double sonKilo;
    private Double degisim;

    private double ortalamaKarbonhidrat;
    private double ortalamaProtein;
    private double ortalamaYag;
}

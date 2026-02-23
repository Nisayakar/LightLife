package com.akif.lightlife.dto.response;

import lombok.*;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HaftalikRaporResponse {

    // "2025-12-01" -> 1800 gibi günlük alınan kaloriler
    private Map<String, Integer> gunlukKaloriler;

    // "2025-12-01" -> 300 gibi günlük yakılan kaloriler
    private Map<String, Integer> gunlukYakilanKaloriler;

    // "2025-12-01" -> 4 gibi günlük öğün sayıları
    private Map<String, Integer> gunlukOgunSayilari;

    // Kilo değişimi
    private Double ilkKilo;
    private Double sonKilo;
    private Double degisim; // son - ilk

    // Haftalık ortalama makrolar (gram)
    private Double ortalamaKarbonhidrat;
    private Double ortalamaProtein;
    private Double ortalamaYag;
}

package com.akif.lightlife.pattern.template;

import com.akif.lightlife.dto.response.HaftalikRaporResponse;

import java.util.Map;

public class HaftalikRapor extends RaporTemplate {

    private HaftalikRaporResponse response;

    public void setResponse(HaftalikRaporResponse response) {
        this.response = response;
    }

    @Override
    protected void baslik() {
        System.out.println("===== HAFTALIK RAPOR =====");
        System.out.println();
    }

    @Override
    protected void govde() {
        if (response == null) {
            System.out.println("Veri bulunamadı.");
            return;
        }

        Map<String, Integer> gunlukKaloriler = response.getGunlukKaloriler();
        Map<String, Integer> gunlukYakilanKaloriler = response.getGunlukYakilanKaloriler();
        Map<String, Integer> gunlukOgunSayilari = response.getGunlukOgunSayilari();

        System.out.println("Günlük Kalori Özeti:");
        if (gunlukKaloriler != null) {
            gunlukKaloriler.forEach((tarih, kal) -> {
                Integer yakilan = gunlukYakilanKaloriler != null
                        ? gunlukYakilanKaloriler.getOrDefault(tarih, 0)
                        : 0;
                Integer ogunSayisi = gunlukOgunSayilari != null
                        ? gunlukOgunSayilari.getOrDefault(tarih, 0)
                        : 0;

                System.out.println(
                        tarih + " -> Alınan: " + kal +
                        " kcal, Yakılan: " + yakilan +
                        " kcal, Öğün: " + ogunSayisi
                );
            });
        }
        System.out.println();

        System.out.println("Kilo Değişimi:");
        System.out.println("- İlk Kilo : " + response.getIlkKilo());
        System.out.println("- Son Kilo : " + response.getSonKilo());
        System.out.println("- Değişim  : " + response.getDegisim());
        System.out.println();

        System.out.println("Haftalık Ortalama Makrolar (gram):");
        System.out.println("- Karbonhidrat: " + response.getOrtalamaKarbonhidrat());
        System.out.println("- Protein     : " + response.getOrtalamaProtein());
        System.out.println("- Yağ         : " + response.getOrtalamaYag());
    }
}

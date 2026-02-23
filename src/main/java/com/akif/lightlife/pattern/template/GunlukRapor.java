package com.akif.lightlife.pattern.template;

import com.akif.lightlife.dto.response.GunlukRaporResponse;

public class GunlukRapor extends RaporTemplate {

    private GunlukRaporResponse response;


    public void setResponse(GunlukRaporResponse response) {
        this.response = response;
    }

    @Override
    protected void baslik() {
        System.out.println("===== GÜNLÜK RAPOR =====");
        if (response != null) {
            System.out.println("Toplam Kalori : " + response.getToplamKalori());
            System.out.println("Yakılan Kalori: " + response.getYakilanKalori());
        }
        System.out.println();
    }

    @Override
    protected void govde() {
        if (response == null) {
            System.out.println("Veri bulunamadı.");
            return;
        }

        System.out.println("Makrolar (gram):");
        System.out.println("- Karbonhidrat: " + response.getKarbonhidrat());
        System.out.println("- Protein     : " + response.getProtein());
        System.out.println("- Yağ         : " + response.getYag());
        System.out.println();

        System.out.println("Öğün Sayısı  : " + response.getOgunSayisi());
        System.out.println("Egzersiz (dk): " + response.getEgzersizDakika());
        System.out.println("Bugünkü kilo : " + response.getBugunKilo());
        System.out.println();

        if (response.getOgunler() != null && !response.getOgunler().isEmpty()) {
            System.out.println("Öğün Özeti:");
            response.getOgunler().forEach(o -> System.out.println("- " + o));
        } else {
            System.out.println("Öğün bulunamadı.");
        }
    }
}

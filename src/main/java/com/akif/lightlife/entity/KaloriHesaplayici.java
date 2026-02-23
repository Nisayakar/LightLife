package com.akif.lightlife.entity;

import com.akif.lightlife.entity.Malzeme;
import com.akif.lightlife.entity.DiyetTarifi;
import org.springframework.stereotype.Component;

@Component
public class KaloriHesaplayici {

    /**
     * Varsayım:
     *  - Yiyecek.kalori alanı 100 gram için kalori değerini tutuyor.
     *  - Malzeme.gram alanı gram cinsinden miktar.
     */
    public int malzemeKalori(Malzeme m) {
        if (m == null || m.getYiyecek() == null) {
            return 0;
        }
        Double yiyecekKalori = m.getYiyecek().getKalori(); // 100g için kalori
        Double gram = m.getGram();                         // bu malzemedeki gram

        if (yiyecekKalori == null || gram == null) {
            return 0;
        }

        // (100g'daki kalori) * (gram / 100)
        double sonuc = yiyecekKalori * (gram / 100.0);
        return (int) Math.round(sonuc);
    }

    /**
     * Diyet tarifinin toplam kalorisi = tüm malzemelerin kalorilerinin toplamı.
     */
    public int tarifToplamKalori(DiyetTarifi t) {
        if (t == null || t.getMalzemeler() == null) {
            return 0;
        }

        return t.getMalzemeler()
                .stream()
                .mapToInt(this::malzemeKalori)
                .sum();
    }
}

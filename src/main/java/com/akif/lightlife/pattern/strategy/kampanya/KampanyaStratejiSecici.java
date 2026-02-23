package com.akif.lightlife.pattern.strategy.kampanya;

import com.akif.lightlife.entity.Kampanya;

public class KampanyaStratejiSecici {

    public static KampanyaStratejisi stratejiSec(Kampanya k) {

        if (k == null || k.getKategori() == null) {
            return tutar -> tutar;
        }

        String kategori = k.getKategori().toUpperCase();   
        int deger = k.getIndirimYuzdesi();                

        return switch (kategori) {
            case "YUZDE"   -> new YuzdeIndirimStratejisi(deger);
            case "SABIT"   -> new TutarIndirimStratejisi(deger);
            case "PREMIUM" -> new PremiumGunStratejisi(deger);
            default        -> tutar -> tutar;
        };
    }
}

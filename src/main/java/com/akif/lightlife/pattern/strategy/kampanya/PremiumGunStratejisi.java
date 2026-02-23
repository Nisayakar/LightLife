package com.akif.lightlife.pattern.strategy.kampanya;

public class PremiumGunStratejisi implements KampanyaStratejisi {

    private final int bedavaGunSayisi; 

    public PremiumGunStratejisi(int bedavaGunSayisi) {
        this.bedavaGunSayisi = bedavaGunSayisi;
    }

    @Override
    public double indirimUygula(double tutar) {
      
        int toplamGun = 30;
        if (bedavaGunSayisi <= 0 || bedavaGunSayisi >= toplamGun) {
            return 0;
        }

        double gunlukUcret = tutar / toplamGun;
        double indirim = gunlukUcret * bedavaGunSayisi;

        double sonuc = tutar - indirim;
        return Math.max(sonuc, 0);
    }
}

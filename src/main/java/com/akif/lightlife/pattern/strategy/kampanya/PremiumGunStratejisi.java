package com.akif.lightlife.pattern.strategy.kampanya;

public class PremiumGunStratejisi implements KampanyaStratejisi {

    private final int gun;

    public PremiumGunStratejisi(int gun) {
        this.gun = gun;
    }

    @Override
    public double indirimUygula(double tutar) {
        double gunIndirim = gun * 5; // günlük 5 TL indirim mantığı
        return Math.max(0, tutar - gunIndirim);
    }
}

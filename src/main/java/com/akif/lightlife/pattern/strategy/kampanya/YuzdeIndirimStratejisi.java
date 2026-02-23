package com.akif.lightlife.pattern.strategy.kampanya;

public class YuzdeIndirimStratejisi implements KampanyaStratejisi {

    private final int yuzde;

    public YuzdeIndirimStratejisi(int yuzde) {
        this.yuzde = yuzde;
    }

    @Override
    public double indirimUygula(double tutar) {
        double indirim = tutar * yuzde / 100.0;
        double sonuc = tutar - indirim;
        return Math.max(sonuc, 0); 
    }
}

package com.akif.lightlife.pattern.strategy.kampanya;

public class YuzdeIndirimStratejisi implements KampanyaStratejisi {

    private final double yuzde;

    public YuzdeIndirimStratejisi(double yuzde) {
        this.yuzde = yuzde;
    }

    @Override
    public double indirimUygula(double tutar) {
        return tutar - (tutar * yuzde / 100);
    }
}

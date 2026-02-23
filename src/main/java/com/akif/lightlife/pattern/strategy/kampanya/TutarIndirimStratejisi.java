package com.akif.lightlife.pattern.strategy.kampanya;

public class TutarIndirimStratejisi implements KampanyaStratejisi {

    private final int indirimTutari; 

    public TutarIndirimStratejisi(int indirimTutari) {
        this.indirimTutari = indirimTutari;
    }

    @Override
    public double indirimUygula(double tutar) {
        double sonuc = tutar - indirimTutari;
        return Math.max(sonuc, 0);
    }
}

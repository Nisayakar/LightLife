package com.akif.lightlife.pattern.strategy.kampanya;

public class TutarIndirimStratejisi implements KampanyaStratejisi {

    private final double indirim;

    public TutarIndirimStratejisi(double indirim) {
        this.indirim = indirim;
    }

    @Override
    public double indirimUygula(double tutar) {
        return Math.max(0, tutar - indirim);
    }
}

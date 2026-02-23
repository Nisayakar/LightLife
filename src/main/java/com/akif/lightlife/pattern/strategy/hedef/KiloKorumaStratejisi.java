package com.akif.lightlife.pattern.strategy.hedef;

public class KiloKorumaStratejisi implements HedefHesaplamaStrateji {

    @Override
    public int gunlukKalori(double bazKalori, double haftalikKiloDegisimKg) {

        return (int) Math.round(bazKalori);
    }
}

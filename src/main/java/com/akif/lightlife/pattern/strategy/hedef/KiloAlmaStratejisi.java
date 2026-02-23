package com.akif.lightlife.pattern.strategy.hedef;

public class KiloAlmaStratejisi implements HedefHesaplamaStrateji {

    private static final double KALORI_PER_KG = 7700.0;

    @Override
    public int gunlukKalori(double bazKalori, double haftalikKiloDegisimKg) {
  
        double hedefKg = Math.abs(haftalikKiloDegisimKg);
        if (hedefKg <= 0) {
          
            return (int) Math.round(bazKalori + 500);
        }

        double haftalikKcal = hedefKg * KALORI_PER_KG;
        double gunlukFazla = haftalikKcal / 7.0;
        double hedef = bazKalori + gunlukFazla;

        return (int) Math.round(hedef);
    }
}

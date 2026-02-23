package com.akif.lightlife.pattern.strategy.hedef;

public class KiloVermeStratejisi implements HedefHesaplamaStrateji {

    private static final double KALORI_PER_KG = 7700.0;

    @Override
    public int gunlukKalori(double bazKalori, double haftalikKiloDegisimKg) {

        double hedefKg = Math.abs(haftalikKiloDegisimKg);
        if (hedefKg <= 0) {
         
            return (int) Math.round(bazKalori - 500);
        }

        double haftalikKcal = hedefKg * KALORI_PER_KG;
        double gunlukAcik = haftalikKcal / 7.0;
        double hedef = bazKalori - gunlukAcik;

        return (int) Math.round(hedef);
    }
}

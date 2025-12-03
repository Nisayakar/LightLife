package com.akif.lightlife.pattern.strategy.hedef;

public class KiloVermeStratejisi implements HedefHesaplamaStrateji {
    public int gunlukKalori(int mevcut) {
        return mevcut - 500;
    }
}

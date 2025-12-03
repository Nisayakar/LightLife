package com.akif.lightlife.pattern.strategy.hedef;

public class KiloAlmaStratejisi implements HedefHesaplamaStrateji {
    public int gunlukKalori(int mevcut) {
        return mevcut + 500;
    }
}

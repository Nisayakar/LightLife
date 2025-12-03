package com.akif.lightlife.util;

public class HesaplamaUtil {

    public static int gunlukKalori(double kilo, double boy, int yas, boolean erkek) {
        if (erkek) {
            return (int) (66 + (13.7 * kilo) + (5 * boy * 100) - (6.8 * yas));
        } else {
            return (int) (655 + (9.6 * kilo) + (1.8 * boy * 100) - (4.7 * yas));
        }
    }
}

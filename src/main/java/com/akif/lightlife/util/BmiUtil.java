package com.akif.lightlife.util;

public class BmiUtil {

    public static double hesapla(double kilo, double boyMetre) {
        return kilo / (boyMetre * boyMetre);
    }

    public static String kategori(double bmi) {
        if (bmi < 18.5) return "Zayıf";
        if (bmi < 25) return "Normal";
        if (bmi < 30) return "Fazla Kilolu";
        return "Obez";
    }
}

package com.akif.lightlife.util;

import com.akif.lightlife.enums.AktiviteDuzeyi;
import com.akif.lightlife.enums.Cinsiyet;

public class HesaplamaUtil {


    public static double hesaplaBmr(Cinsiyet cinsiyet, double kilo, double boy, int yas) {
  
        if (cinsiyet == Cinsiyet.ERKEK) {
            return 10 * kilo + 6.25 * boy - 5 * yas + 5;
        } else {
            return 10 * kilo + 6.25 * boy - 5 * yas - 161;
        }
    }

    public static double aktiviteCarpani(AktiviteDuzeyi d) {
        return switch (d) {
            case HAREKETSIZ    -> 1.2;
            case HAFIF_AKTIF   -> 1.375;
            case ORTA_AKTIF    -> 1.55;
            case COK_AKTIF     -> 1.725;
            case ASIRI_AKTIF   -> 1.9;
        };
    }

  
    public static double bazKalori(Cinsiyet cinsiyet,
                                   double kilo,
                                   double boy,
                                   int yas,
                                   AktiviteDuzeyi aktiviteDuzeyi) {

        double bmr = hesaplaBmr(cinsiyet, kilo, boy, yas);
        double carpani = aktiviteCarpani(aktiviteDuzeyi);

        return bmr * carpani;
    }
}

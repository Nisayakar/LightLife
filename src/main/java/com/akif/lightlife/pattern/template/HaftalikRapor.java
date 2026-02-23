package com.akif.lightlife.pattern.template;

public class HaftalıkRapor extends RaporTemplate {

    protected void baslik() {
        System.out.println("HAFTALIK RAPOR");
    }

    protected void govde() {
        System.out.println("7 günlük analiz tamamlandı.");
    }
}

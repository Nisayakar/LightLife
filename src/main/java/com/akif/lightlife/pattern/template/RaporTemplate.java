package com.akif.lightlife.pattern.template;

public abstract class RaporTemplate {

    public final void raporOlustur() {
        baslik();
        govde();
        altBilgi();
    }

    protected abstract void baslik();
    protected abstract void govde();

    protected void altBilgi() {
        System.out.println("LightLife Rapor Sistemi");
    }
}



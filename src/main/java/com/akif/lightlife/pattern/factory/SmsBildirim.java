package com.akif.lightlife.pattern.factory;

public class SmsBildirim implements Bildirim {
    @Override
    public void gonder(String hedef, String mesaj) {
        System.out.println("SMS gönderildi: " + hedef + " - " + mesaj);
    }
}

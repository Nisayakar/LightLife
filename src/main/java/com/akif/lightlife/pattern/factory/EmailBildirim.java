package com.akif.lightlife.pattern.factory;

public class EmailBildirim implements Bildirim {
    @Override
    public void gonder(String hedef, String mesaj) {
        System.out.println("EMAIL gönderildi: " + hedef + " - " + mesaj);
    }
}

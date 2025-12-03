package com.akif.lightlife.pattern.factory;

public class PushBildirim implements Bildirim {
    @Override
    public void gonder(String hedef, String mesaj) {
        System.out.println("PUSH gönderildi: " + hedef + " - " + mesaj);
    }
}

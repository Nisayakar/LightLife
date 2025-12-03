package com.akif.lightlife.pattern.observer;

public class KullaniciBildirimObserver implements BildirimObserver {
    @Override
    public void bildir(BildirimOlayi olay) {
        System.out.println("Kullanıcı bildirimi: " + olay.mesaj());
    }
}

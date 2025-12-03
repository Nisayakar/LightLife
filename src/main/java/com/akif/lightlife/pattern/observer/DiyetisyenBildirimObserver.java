package com.akif.lightlife.pattern.observer;

public class DiyetisyenBildirimObserver implements BildirimObserver {
    @Override
    public void bildir(BildirimOlayi olay) {
        System.out.println("Diyetisyen bildirimi: " + olay.mesaj());
    }
}

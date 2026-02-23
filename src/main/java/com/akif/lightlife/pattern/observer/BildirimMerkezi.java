package com.akif.lightlife.pattern.observer;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BildirimMerkezi {

    private final List<BildirimObserver> observerList;


    public BildirimMerkezi(List<BildirimObserver> observerList) {
        this.observerList = observerList;
    }

    public void bildir(BildirimOlayi olay) {
        for (BildirimObserver observer : observerList) {
            observer.bildir(olay);
        }
    }
}

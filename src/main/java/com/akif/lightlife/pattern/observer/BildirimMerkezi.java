package com.akif.lightlife.pattern.observer;

import java.util.ArrayList;
import java.util.List;

public class BildirimMerkezi {

    private final List<BildirimObserver> observerList = new ArrayList<>();

    public void ekle(BildirimObserver observer) {
        observerList.add(observer);
    }

    public void sil(BildirimObserver observer) {
        observerList.remove(observer);
    }

    public void yayinla(BildirimOlayi olay) {
        observerList.forEach(o -> o.bildir(olay));
    }
}

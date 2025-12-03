package com.akif.lightlife.pattern.observer;

public record BildirimOlayi(
        Long kullaniciId,
        String hedef,
        String baslik,
        String mesaj
) {}

package com.akif.lightlife.pattern.observer;

import com.akif.lightlife.enums.BildirimHedefTipi;

public record BildirimOlayi(
        Long hedefId,
        String baslik,
        String mesaj,
        BildirimHedefTipi hedefTipi
) {}

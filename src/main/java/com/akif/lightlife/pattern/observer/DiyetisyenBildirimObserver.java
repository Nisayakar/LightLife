package com.akif.lightlife.pattern.observer;


import com.akif.lightlife.pattern.factory.Bildirim;
import com.akif.lightlife.pattern.factory.BildirimFactory;
import com.akif.lightlife.repository.DiyetisyenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.akif.lightlife.enums.BildirimHedefTipi;


@Component
@RequiredArgsConstructor
public class DiyetisyenBildirimObserver implements BildirimObserver {

    private final DiyetisyenRepository diyetisyenRepository;

    @Override
    public void bildir(BildirimOlayi olay) {

        if (olay.hedefTipi() != BildirimHedefTipi.DIYETISYEN) return;

        var d = diyetisyenRepository.findById(olay.hedefId()).orElse(null);
        if (d == null || d.getEmail() == null) return;

        Bildirim bildirim = BildirimFactory.olustur("EMAIL");
        bildirim.gonder(
                d.getEmail(),
                olay.baslik() + " - " + olay.mesaj()
        );
    }
}


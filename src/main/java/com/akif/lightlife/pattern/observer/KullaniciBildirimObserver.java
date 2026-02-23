package com.akif.lightlife.pattern.observer;

import com.akif.lightlife.pattern.factory.Bildirim;
import com.akif.lightlife.pattern.factory.BildirimFactory;
import com.akif.lightlife.repository.KullaniciRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.akif.lightlife.enums.BildirimHedefTipi;

@Component
@RequiredArgsConstructor
public class KullaniciBildirimObserver implements BildirimObserver {

    private final KullaniciRepository kullaniciRepository;

    @Override
    public void bildir(BildirimOlayi olay) {

        if (olay.hedefTipi() != BildirimHedefTipi.KULLANICI) return;

    
        var kullanici = kullaniciRepository.findById(olay.hedefId()).orElse(null);
        
       
        if (kullanici == null || kullanici.getTelefon() == null) return;

      
        Bildirim bildirim = BildirimFactory.olustur("SMS");
        
       
        bildirim.gonder(
                kullanici.getTelefon(),
                olay.baslik() + " - " + olay.mesaj()
        );
    }
}
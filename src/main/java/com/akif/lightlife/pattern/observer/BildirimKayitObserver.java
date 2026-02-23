package com.akif.lightlife.pattern.observer;

import com.akif.lightlife.entity.Bildirim;
import com.akif.lightlife.repository.BildirimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class BildirimKayitObserver implements BildirimObserver {

    private final BildirimRepository bildirimRepository;

    @Override
    public void bildir(BildirimOlayi olay) {


        Bildirim b = Bildirim.builder()
                .kullaniciId(olay.hedefId()) 
                .baslik(olay.baslik())
                .mesaj(olay.mesaj())
                .okunduMu(false)
                .tip(olay.hedefTipi().toString()) 
                .kaynak(olay.hedefTipi().name())
                .tarih(LocalDateTime.now())
                .build();

        bildirimRepository.save(b);
    }
}
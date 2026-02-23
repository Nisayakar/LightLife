package com.akif.lightlife.service.impl;

import com.akif.lightlife.dto.request.OgunEkleRequest;
import com.akif.lightlife.dto.response.OgunResponse;
import com.akif.lightlife.entity.*;
import com.akif.lightlife.enums.BildirimHedefTipi;
import com.akif.lightlife.exception.NotFoundException;
import com.akif.lightlife.pattern.observer.BildirimMerkezi;
import com.akif.lightlife.pattern.observer.BildirimOlayi;
import com.akif.lightlife.repository.*;
import com.akif.lightlife.service.OgunService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OgunServiceImpl implements OgunService {

    private final OgunRepository ogunRepository;
    private final KullaniciRepository kullaniciRepository;
    private final DiyetTarifiRepository diyetTarifiRepository; // 🔥 EKLENDİ
    private final OgunDiyetTarifiRepository ogunDiyetTarifiRepository; // 🔥 EKLENDİ
    private final BildirimMerkezi bildirimMerkezi;

    @Override
    @Transactional // 🔥 ÖNEMLİ: İki tabloya birden kayıt yaptığı için atomik olmalı
    public OgunResponse ogunEkle(OgunEkleRequest request) {

        // 1. Kullanıcıyı bul
        Kullanici danisan = kullaniciRepository.findById(request.getKullaniciId())
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));

        // 2. Tarifi bul (Eğer frontend'den tarifId gelmiyorsa hata verdirir)
        DiyetTarifi tarif = diyetTarifiRepository.findById(request.getTarifId())
                .orElseThrow(() -> new NotFoundException("Seçilen tarif bulunamadı"));

        LocalDate tarih = request.getTarih() != null ? request.getTarih() : LocalDate.now();

        // 3. ÖNCE ÖĞÜNÜ OLUŞTUR (SABAH, OGLE vb.)
        Ogun ogun = Ogun.builder()
                .kullanici(danisan)
                .tarih(tarih)
                .tip(request.getTip())
                .build();

        Ogun kaydedilenOgun = ogunRepository.save(ogun);

        // 4. 🔥 KRİTİK ADIM: ÖĞÜNÜ SEÇİLEN TARİFLE BAĞLA (İçeriğin gözükmesini sağlayan yer)
        OgunDiyetTarifi baglanti = OgunDiyetTarifi.builder()
                .ogun(kaydedilenOgun)
                .tarif(tarif)
                .porsiyon(request.getPorsiyon() > 0 ? request.getPorsiyon() : 1)
                .build();

        ogunDiyetTarifiRepository.save(baglanti);

        // 5. 🔔 OBSERVER → DİYETİSYENE BİLDİR
        if (danisan.getDiyetisyen() != null) {
            bildirimMerkezi.bildir(
                new BildirimOlayi(
                    danisan.getDiyetisyen().getId(),
                    "Yeni Öğün Kaydı",
                    danisan.getAd() + " " + danisan.getSoyad() 
                            + " menüsüne " + tarif.getAd() + " ekledi.", 
                    BildirimHedefTipi.DIYETISYEN
                )
            );
        }

        return mapToResponse(kaydedilenOgun);
    }

    @Override
    public List<OgunResponse> bugunOgunleri(Long kullaniciId) {
        LocalDate bugun = LocalDate.now();
        return ogunRepository
                .findByKullaniciIdAndTarih(kullaniciId, bugun)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private OgunResponse mapToResponse(Ogun ogun) {
        return OgunResponse.builder()
                .id(ogun.getId())
                .kullaniciId(ogun.getKullanici().getId())
                .tarih(ogun.getTarih())
                .tip(ogun.getTip())
                .build();
    }
}
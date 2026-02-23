package com.akif.lightlife.service.impl;

import com.akif.lightlife.dto.request.OdemeYapRequest;
import com.akif.lightlife.dto.response.OdemeResponse;
import com.akif.lightlife.entity.Abonelik;
import com.akif.lightlife.entity.Odeme;
import com.akif.lightlife.exception.NotFoundException;
import com.akif.lightlife.mapper.OdemeMapper;
import com.akif.lightlife.repository.AbonelikRepository;
import com.akif.lightlife.repository.OdemeRepository;
import com.akif.lightlife.service.OdemeService;
import com.akif.lightlife.service.KampanyaService;
import com.akif.lightlife.service.KullaniciService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OdemeServiceImpl implements OdemeService {

    private final OdemeRepository odemeRepo;
    private final AbonelikRepository abonelikRepo;
    private final OdemeMapper mapper;
    private final KampanyaService kampanyaService;
    private final KullaniciService kullaniciService;

    @Override
    @Transactional
    public OdemeResponse odemeYap(OdemeYapRequest r) {
        // 1. Aboneliği kontrol et
        Abonelik abonelik = abonelikRepo.findById(r.getAbonelikId())
                .orElseThrow(() -> new NotFoundException("Abonelik bulunamadı"));

        // 2. Başlangıç tutarını al (Request'ten gelen ham fiyat)
        BigDecimal sonTutar = (r.getTutar() != null) ? r.getTutar() : BigDecimal.ZERO; 

        // 3. EĞER KAMPANYA SEÇİLMİŞSE: İndirimi burada uygula
        if (r.getKampanyaId() != null) {
            // Strategy Pattern kullanarak indirimli tutarı hesaplar
            double indirimliDouble = kampanyaService.indirimliTutarHesapla(r.getKampanyaId(), sonTutar.doubleValue());
            sonTutar = BigDecimal.valueOf(indirimliDouble); 
        }

        // 4. Ödeme kaydını indirimli tutar ile oluştur
        Odeme odeme = Odeme.builder()
                .abonelik(abonelik)
                .tutar(sonTutar) // 500 yerine indirimli hali (örn: 450) buraya gelir
                .odemeYontemi(r.getOdemeYontemi())
                .durum(Odeme.OdemeDurumu.BASARILI) 
                .tarih(LocalDateTime.now())
                .islemNo(UUID.randomUUID().toString())
                .build();
        
        odemeRepo.save(odeme);

        // 5. Aboneliği aktifleştir ve diyetisyeni ata
        abonelik.setAktifMi(true);
        abonelikRepo.save(abonelik);

        if (abonelik.getKullanici() != null && abonelik.getDiyetisyen() != null) {
            kullaniciService.diyetisyenAta(
                abonelik.getKullanici().getId(), 
                abonelik.getDiyetisyen().getId()
            );
        }

        return mapper.toResponse(odeme);
    }

    @Override
    public List<OdemeResponse> abonelikOdemeleri(Long abonelikId) {
        Abonelik abonelik = abonelikRepo.findById(abonelikId)
                .orElseThrow(() -> new NotFoundException("Abonelik bulunamadı"));

        return odemeRepo.findByAbonelik(abonelik).stream()
                .map(mapper::toResponse)
                .toList();
    }
}
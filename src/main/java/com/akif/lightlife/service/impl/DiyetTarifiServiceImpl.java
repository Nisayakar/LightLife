package com.akif.lightlife.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akif.lightlife.dto.request.MalzemeRequest;
import com.akif.lightlife.dto.request.TarifOlusturRequest;
import com.akif.lightlife.dto.response.TarifResponse;
import com.akif.lightlife.entity.DiyetTarifi;
import com.akif.lightlife.entity.Diyetisyen;
import com.akif.lightlife.entity.Kullanici;
import com.akif.lightlife.entity.Malzeme;
import com.akif.lightlife.enums.BildirimHedefTipi;
import com.akif.lightlife.exception.NotFoundException;
import com.akif.lightlife.mapper.DiyetTarifiMapper;
import com.akif.lightlife.pattern.observer.BildirimMerkezi;
import com.akif.lightlife.pattern.observer.BildirimOlayi;
import com.akif.lightlife.repository.DiyetTarifiRepository;
import com.akif.lightlife.repository.DiyetisyenRepository;
import com.akif.lightlife.repository.KullaniciRepository;
import com.akif.lightlife.repository.MalzemeRepository;
import com.akif.lightlife.repository.YiyecekRepository;
import com.akif.lightlife.service.DiyetTarifiService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiyetTarifiServiceImpl implements DiyetTarifiService {

    private final DiyetTarifiRepository tarifRepo;
    private final MalzemeRepository malzemeRepo;
    private final YiyecekRepository yiyecekRepo;
    private final DiyetisyenRepository diyetisyenRepo;
    private final KullaniciRepository kullaniciRepository;
    private final DiyetTarifiMapper mapper;
    private final BildirimMerkezi bildirimMerkezi;

    @Override
    @Transactional
    public TarifResponse tarifOlustur(TarifOlusturRequest r) {

        // 1️⃣ Diyetisyen kontrolü
        Diyetisyen d = diyetisyenRepo.findById(r.getDiyetisyenId())
                .orElseThrow(() -> new NotFoundException("Diyetisyen bulunamadı"));

        // 2️⃣ Toplam kalori hesapla
        int toplamKalori = 0;

        for (MalzemeRequest mr : r.getMalzemeler()) {
            var yiyecek = yiyecekRepo.findById(mr.getYiyecekId())
                    .orElseThrow(() -> new NotFoundException("Yiyecek bulunamadı"));

            int kalori = (int) Math.round(
                    (yiyecek.getKalori() / 100.0) * mr.getGram()
            );
            toplamKalori += kalori;
        }

        // 3️⃣ Tarif kaydı
        DiyetTarifi tarif = DiyetTarifi.builder()
                .ad(r.getAd())
                .aciklama(r.getAciklama())
                .diyetisyen(d)
                .toplamKalori(toplamKalori)
                .build();

        tarif = tarifRepo.save(tarif);

        // 4️⃣ Malzemeleri kaydet
        for (MalzemeRequest mr : r.getMalzemeler()) {
            var yiyecek = yiyecekRepo.findById(mr.getYiyecekId()).get();

            Malzeme m = Malzeme.builder()
                    .gram(mr.getGram())
                    .yiyecek(yiyecek)
                    .tarif(tarif)
                    .build();

            malzemeRepo.save(m);
        }

        // 5️⃣ OBSERVER – TÜM DANIŞANLARA BİLDİRİM
        List<Kullanici> danisanlar =
                kullaniciRepository.findByDiyetisyenId(d.getId());

        for (Kullanici danisan : danisanlar) {
            bildirimMerkezi.bildir(new BildirimOlayi(
                    danisan.getId(),
                    "Yeni Tarif Yayında!",
                    d.getAd() + " diyetisyeniniz yeni bir tarif ekledi: " + tarif.getAd(),
                    BildirimHedefTipi.KULLANICI
            ));
        }

       
        return mapper.toResponse(tarif);
    }

    @Override
    public TarifResponse tarifGetir(Long id) {
        return tarifRepo.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new NotFoundException("Tarif bulunamadı"));
    }

    @Override
    public List<TarifResponse> tarifListele() {
        return tarifRepo.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}

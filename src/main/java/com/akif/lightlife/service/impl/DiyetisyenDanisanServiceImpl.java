package com.akif.lightlife.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akif.lightlife.dto.response.DanisanOzetResponse;
import com.akif.lightlife.entity.Diyetisyen;
import com.akif.lightlife.entity.Kullanici;
import com.akif.lightlife.entity.KullaniciDiyetisyen;
import com.akif.lightlife.repository.DiyetisyenRepository;
import com.akif.lightlife.repository.KullaniciDiyetisyenRepository;
import com.akif.lightlife.repository.KullaniciRepository;
import com.akif.lightlife.service.DiyetisyenDanisanService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DiyetisyenDanisanServiceImpl implements DiyetisyenDanisanService {

    private final KullaniciDiyetisyenRepository kdRepo;
    private final KullaniciRepository kullaniciRepo;
    private final DiyetisyenRepository diyetisyenRepo;

    @Override
    public void danisanEkle(Long diyetisyenId, Long kullaniciId) {

        boolean exists = kdRepo.existsByKullaniciIdAndDiyetisyenIdAndBitisTarihiIsNull(kullaniciId, diyetisyenId);
        if (exists) return;

        Kullanici k = kullaniciRepo.findById(kullaniciId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        Diyetisyen d = diyetisyenRepo.findById(diyetisyenId)
                .orElseThrow(() -> new RuntimeException("Diyetisyen bulunamadı"));

        KullaniciDiyetisyen kd = KullaniciDiyetisyen.builder()
                .kullanici(k)
                .diyetisyen(d)
                .build();

        kdRepo.save(kd);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DanisanOzetResponse> diyetisyeninDanisanlari(Long diyetisyenId) {
        return kdRepo.findByDiyetisyenIdAndBitisTarihiIsNull(diyetisyenId)
                .stream()
                .map(kd -> DanisanOzetResponse.builder()
                        .id(kd.getKullanici().getId())
                        .ad(kd.getKullanici().getAd())
                        .soyad(kd.getKullanici().getSoyad())
                        .email(kd.getKullanici().getEmail())
                        .telefon(kd.getKullanici().getTelefon())
                        .build()
                )
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DanisanOzetResponse> kullanicininDiyetisyenleri(Long kullaniciId) {
        return kdRepo.findByKullaniciIdAndBitisTarihiIsNull(kullaniciId)
                .stream()
                .map(kd -> DanisanOzetResponse.builder()
                        .id(kd.getDiyetisyen().getId())
                        .ad(kd.getDiyetisyen().getAd())
                        .soyad(kd.getDiyetisyen().getSoyad())
                        .email(kd.getDiyetisyen().getEmail())
                        .telefon(kd.getDiyetisyen().getTelefon())
                        .build()
                )
                .toList();
    }
}

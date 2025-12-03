package com.akif.lightlife.service.impl;

import com.akif.lightlife.dto.request.AbonelikOlusturRequest;
import com.akif.lightlife.dto.response.AbonelikResponse;
import com.akif.lightlife.entity.Abonelik;
import com.akif.lightlife.entity.Diyetisyen;
import com.akif.lightlife.entity.Kullanici;
import com.akif.lightlife.exception.NotFoundException;
import com.akif.lightlife.mapper.AbonelikMapper;
import com.akif.lightlife.repository.AbonelikRepository;
import com.akif.lightlife.repository.DiyetisyenRepository;
import com.akif.lightlife.repository.KullaniciRepository;
import com.akif.lightlife.service.AbonelikService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AbonelikServiceImpl implements AbonelikService {

    private final AbonelikRepository abonelikRepo;
    private final KullaniciRepository kullaniciRepo;
    private final DiyetisyenRepository diyetisyenRepo;
    private final AbonelikMapper mapper;

    @Override
    public AbonelikResponse abonelikOlustur(AbonelikOlusturRequest r) {

        Kullanici k = kullaniciRepo.findById(r.getKullaniciId())
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));

        Diyetisyen d = diyetisyenRepo.findById(r.getDiyetisyenId())
                .orElseThrow(() -> new NotFoundException("Diyetisyen bulunamadı"));

        LocalDate baslangic = LocalDate.now();
        LocalDate bitis = baslangic.plusMonths(r.getSureAy());

        Abonelik a = Abonelik.builder()
                .kullanici(k)
                .diyetisyen(d)
                .planAdi(r.getPlanAdi())
                .sureAy(r.getSureAy())
                .aylikUcret(r.getAylikUcret())
                .baslangicTarihi(baslangic)
                .bitisTarihi(bitis)
                .aktifMi(true)
                .build();

        abonelikRepo.save(a);

        return mapper.toResponse(a);
    }

    @Override
    public List<AbonelikResponse> kullaniciAbonelikleri(Long kullaniciId) {

        Kullanici k = kullaniciRepo.findById(kullaniciId)
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));

        return abonelikRepo.findByKullanici(k)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public AbonelikResponse abonelikIptal(Long abonelikId) {

        Abonelik a = abonelikRepo.findById(abonelikId)
                .orElseThrow(() -> new NotFoundException("Abonelik bulunamadı"));

        a.setAktifMi(false);
        a.setBitisTarihi(LocalDate.now());
        abonelikRepo.save(a);

        return mapper.toResponse(a);
    }
}

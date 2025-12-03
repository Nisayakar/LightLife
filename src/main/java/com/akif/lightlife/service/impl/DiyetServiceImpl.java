package com.akif.lightlife.service.impl;

import com.akif.lightlife.dto.request.DiyetOlusturRequest;
import com.akif.lightlife.dto.response.DiyetResponse;
import com.akif.lightlife.entity.Diyet;
import com.akif.lightlife.entity.Diyetisyen;
import com.akif.lightlife.entity.Kullanici;
import com.akif.lightlife.exception.NotFoundException;
import com.akif.lightlife.mapper.DiyetMapper;
import com.akif.lightlife.repository.DiyetRepository;
import com.akif.lightlife.repository.KullaniciRepository;
import com.akif.lightlife.repository.DiyetisyenRepository;
import com.akif.lightlife.service.DiyetService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiyetServiceImpl implements DiyetService {

    private final DiyetRepository repo;
    private final KullaniciRepository kullaniciRepo;
    private final DiyetisyenRepository diyetisyenRepo;
    private final DiyetMapper mapper;

    @Override
    public DiyetResponse olustur(DiyetOlusturRequest r) {

        Kullanici k = kullaniciRepo.findById(r.getKullaniciId())
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));

        Diyetisyen d = diyetisyenRepo.findById(r.getDiyetisyenId())
                .orElseThrow(() -> new NotFoundException("Diyetisyen bulunamadı"));

        Diyet diyet = Diyet.builder()
                .kullanici(k)
                .diyetisyen(d)
                .diyetAdi(r.getDiyetAdi())
                .gunlukKaloriHedefi(r.getGunlukKaloriHedefi())
                .karbonhidratHedefi(r.getKarbonhidratHedefi())
                .proteinHedefi(r.getProteinHedefi())
                .yagHedefi(r.getYagHedefi())
                .sabah(r.getSabah())
                .ogle(r.getOgle())
                .aksam(r.getAksam())
                .baslangicTarihi(LocalDate.parse(r.getBaslangicTarihi()))
                .bitisTarihi(LocalDate.parse(r.getBitisTarihi()))
                .aktifMi(true)
                .build();

        repo.save(diyet);

        return mapper.toResponse(diyet);
    }

    @Override
    public List<DiyetResponse> kullaniciAktifDiyetleri(Long kullaniciId) {

        Kullanici k = kullaniciRepo.findById(kullaniciId)
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));

        return repo.findByKullaniciAndAktifMiTrue(k)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public DiyetResponse diyetBitir(Long diyetId) {

        Diyet d = repo.findById(diyetId)
                .orElseThrow(() -> new NotFoundException("Diyet bulunamadı"));

        d.setAktifMi(false);
        d.setBitisTarihi(LocalDate.now());

        repo.save(d);

        return mapper.toResponse(d);
    }
}

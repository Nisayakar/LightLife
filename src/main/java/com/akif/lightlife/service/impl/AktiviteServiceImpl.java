package com.akif.lightlife.service.impl;

import com.akif.lightlife.dto.request.AktiviteEkleRequest;
import com.akif.lightlife.dto.response.AktiviteResponse;
import com.akif.lightlife.entity.Aktivite;
import com.akif.lightlife.entity.Kullanici;
import com.akif.lightlife.exception.NotFoundException;
import com.akif.lightlife.mapper.AktiviteMapper;
import com.akif.lightlife.repository.AktiviteRepository;
import com.akif.lightlife.repository.KullaniciRepository;
import com.akif.lightlife.service.AktiviteService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AktiviteServiceImpl implements AktiviteService {

    private final AktiviteRepository repo;
    private final KullaniciRepository kullaniciRepo;
    private final AktiviteMapper mapper;

    @Override
    public AktiviteResponse ekle(AktiviteEkleRequest r) {

        Kullanici k = kullaniciRepo.findById(r.getKullaniciId())
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));

        double mesafeKm = r.getAdimSayisi() * 0.00075;

        int kalori = (int) (r.getAdimSayisi() * 0.04);

        Aktivite a = Aktivite.builder()
                .adimSayisi(r.getAdimSayisi())
                .mesafeKm(mesafeKm)
                .sureDakika(r.getSureDakika())
                .yakilanKalori(kalori)
                .tarih(LocalDate.now())
                .kullanici(k)
                .build();

        repo.save(a);

        return mapper.toResponse(a);
    }

    @Override
    public List<AktiviteResponse> kullaniciGunlukAktivite(Long kullaniciId) {

        Kullanici k = kullaniciRepo.findById(kullaniciId)
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));

        return repo.findByKullaniciAndTarih(k, LocalDate.now())
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<AktiviteResponse> kullaniciTumAktiviteler(Long kullaniciId) {

        Kullanici k = kullaniciRepo.findById(kullaniciId)
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));

        return repo.findByKullanici(k)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}

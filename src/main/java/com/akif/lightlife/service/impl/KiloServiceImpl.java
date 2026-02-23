package com.akif.lightlife.service.impl;

import com.akif.lightlife.dto.request.KiloKayitRequest;
import com.akif.lightlife.dto.response.KiloResponse;
import com.akif.lightlife.entity.Kilo;
import com.akif.lightlife.entity.Kullanici;
import com.akif.lightlife.exception.NotFoundException;
import com.akif.lightlife.mapper.KiloMapper;
import com.akif.lightlife.repository.KiloRepository;
import com.akif.lightlife.repository.KullaniciRepository;
import com.akif.lightlife.service.KiloService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KiloServiceImpl implements KiloService {
    private final KiloRepository kiloRepo;
    private final KullaniciRepository kullaniciRepo;
    private final KiloMapper mapper;

    @Override
    public KiloResponse ekle(KiloKayitRequest request) {

        Kullanici k = kullaniciRepo.findById(request.getKullaniciId())
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));

        Kilo kilo = Kilo.builder()
                .kiloDeger(request.getKiloDeger())
                .tarih(LocalDate.now())
                .kullanici(k)
                .build();

        kiloRepo.save(kilo);

        return mapper.toResponse(kilo);
    }

    @Override
    public List<KiloResponse> kullaniciKilolari(Long kullaniciId) {

        Kullanici k = kullaniciRepo.findById(kullaniciId)
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));

        return kiloRepo.findByKullanici(k)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public KiloResponse bugunKilo(Long kullaniciId) {

        Kullanici k = kullaniciRepo.findById(kullaniciId)
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));

        return kiloRepo.findByKullaniciAndTarih(k, LocalDate.now())
                .stream()
                .findFirst()
                .map(mapper::toResponse)
                .orElseThrow(() -> new NotFoundException("Bugün için kilo kaydı bulunamadı"));
    }
}

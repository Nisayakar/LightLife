package com.akif.lightlife.service.impl;

import com.akif.lightlife.dto.request.EgzersizKayitRequest;
import com.akif.lightlife.dto.response.EgzersizResponse;
import com.akif.lightlife.entity.Egzersiz;
import com.akif.lightlife.entity.Kullanici;
import com.akif.lightlife.exception.NotFoundException;
import com.akif.lightlife.repository.EgzersizRepository;
import com.akif.lightlife.repository.KullaniciRepository;
import com.akif.lightlife.mapper.EgzersizMapper;
import com.akif.lightlife.service.EgzersizService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EgzersizServiceImpl implements EgzersizService {

    private final EgzersizRepository repo;
    private final KullaniciRepository kullaniciRepo;
    private final EgzersizMapper mapper;

    @Override
    public EgzersizResponse ekle(EgzersizKayitRequest r) {

        Kullanici k = kullaniciRepo.findById(r.getKullaniciId())
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));

        Egzersiz e = Egzersiz.builder()
                .ad(r.getAd())
                .sureDakika(r.getSureDakika())
                .kalori(r.getKalori())
                .tarih(LocalDate.now())
                .kullanici(k)
                .build();

        repo.save(e);

        return mapper.toResponse(e);
    }

    @Override
    public List<EgzersizResponse> kullaniciEgzersizleri(Long kullaniciId) {

        Kullanici k = kullaniciRepo.findById(kullaniciId)
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));

        return repo.findByKullanici(k)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<EgzersizResponse> bugununEgzersizleri(Long kullaniciId) {

        Kullanici k = kullaniciRepo.findById(kullaniciId)
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));

        LocalDate today = LocalDate.now();

        return repo.findByKullaniciAndTarih(k, today)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}

package com.akif.lightlife.service.impl;

import com.akif.lightlife.dto.request.YiyecekKayitRequest;
import com.akif.lightlife.dto.request.YiyecekGuncelleRequest;
import com.akif.lightlife.dto.response.YiyecekResponse;
import com.akif.lightlife.entity.Yiyecek;
import com.akif.lightlife.mapper.YiyecekMapper;
import com.akif.lightlife.repository.YiyecekRepository;
import com.akif.lightlife.service.YiyecekService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class YiyecekServiceImpl implements YiyecekService {

    private final YiyecekRepository repo;
    private final YiyecekMapper mapper;

    @Override
    public YiyecekResponse ekle(YiyecekKayitRequest r) {

        if (repo.existsByAd(r.getAd())) {
            throw new RuntimeException("Bu yiyecek zaten kayıtlı.");
        }

        Yiyecek y = Yiyecek.builder()
                .ad(r.getAd())
                .kalori(r.getKalori())
                .karbonhidrat(r.getKarbonhidrat())
                .protein(r.getProtein())
                .yag(r.getYag())
                .build();

        repo.save(y);

        return mapper.toResponse(y);
    }

    @Override
    public YiyecekResponse guncelle(Long id, YiyecekGuncelleRequest r) {

        Yiyecek y = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Yiyecek bulunamadı"));

        y.setAd(r.getAd());
        y.setKalori(r.getKalori());
        y.setKarbonhidrat(r.getKarbonhidrat());
        y.setProtein(r.getProtein());
        y.setYag(r.getYag());

        repo.save(y);

        return mapper.toResponse(y);
    }

    @Override
    public void sil(Long id) {
        repo.deleteById(id);
    }

    @Override
    public YiyecekResponse getir(Long id) {
        return repo.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Yiyecek bulunamadı"));
    }

    @Override
    public List<YiyecekResponse> listele() {
        return repo.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}

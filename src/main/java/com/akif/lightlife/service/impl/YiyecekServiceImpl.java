package com.akif.lightlife.service.impl;

import com.akif.lightlife.dto.request.YiyecekGuncelleRequest;
import com.akif.lightlife.dto.request.YiyecekKayitRequest;
import com.akif.lightlife.dto.response.YiyecekResponse;
import com.akif.lightlife.entity.Yiyecek;
import com.akif.lightlife.repository.YiyecekRepository;
import com.akif.lightlife.service.YiyecekService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class YiyecekServiceImpl implements YiyecekService {

    private final YiyecekRepository repo;

    @Override
    public YiyecekResponse ekle(YiyecekKayitRequest r) {

        Yiyecek y = Yiyecek.builder()
                .ad(r.getAd())
                .kalori(r.getKalori())
                .karbonhidrat(r.getKarbonhidrat())
                .protein(r.getProtein())
                .yag(r.getYag())
                .build();

        y = repo.save(y);

        return toResponse(y);
    }

    @Override
    public List<YiyecekResponse> getAll() {
        return repo.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public YiyecekResponse guncelle(Long id, YiyecekGuncelleRequest request) {

        Yiyecek yiyecek = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Yiyecek bulunamadı (id=" + id + ")"));

      
        if (request.getAd() != null) {
            yiyecek.setAd(request.getAd());
        }
        if (request.getKalori() != null) {
            yiyecek.setKalori(request.getKalori());
        }
        if (request.getKarbonhidrat() != null) {
            yiyecek.setKarbonhidrat(request.getKarbonhidrat());
        }
        if (request.getProtein() != null) {
            yiyecek.setProtein(request.getProtein());
        }
        if (request.getYag() != null) {
            yiyecek.setYag(request.getYag());
        }

        yiyecek = repo.save(yiyecek);

        return toResponse(yiyecek);
    }

    @Override
    public void sil(Long id) {
     
        if (!repo.existsById(id)) {
            throw new RuntimeException("Silinecek yiyecek bulunamadı (id=" + id + ")");
        }
        repo.deleteById(id);
    }

    @Override
    public YiyecekResponse getir(Long id) {
        Yiyecek yiyecek = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Yiyecek bulunamadı (id=" + id + ")"));

        return toResponse(yiyecek);
    }

    @Override
    public List<YiyecekResponse> listele() {
      
        return repo.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

 
    private YiyecekResponse toResponse(Yiyecek y) {
        return YiyecekResponse.builder()
                .id(y.getId())
                .ad(y.getAd())
                .kalori(y.getKalori())
                .karbonhidrat(y.getKarbonhidrat())
                .protein(y.getProtein())
                .yag(y.getYag())
                .build();
    }
}

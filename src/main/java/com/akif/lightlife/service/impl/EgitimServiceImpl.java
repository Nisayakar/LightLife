package com.akif.lightlife.service.impl;

import com.akif.lightlife.dto.request.EgitimOlusturRequest;
import com.akif.lightlife.dto.response.EgitimResponse;
import com.akif.lightlife.entity.Egitim;
import com.akif.lightlife.exception.NotFoundException;
import com.akif.lightlife.mapper.EgitimMapper;
import com.akif.lightlife.repository.EgitimRepository;
import com.akif.lightlife.service.EgitimService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EgitimServiceImpl implements EgitimService {

    private final EgitimRepository repo;
    private final EgitimMapper mapper;

    @Override
    public EgitimResponse olustur(EgitimOlusturRequest r) {

        Egitim e = Egitim.builder()
                .diyetisyenId(r.getDiyetisyenId())
                .baslik(r.getBaslik())
                .icerik(r.getIcerik())
                .kategori(r.getKategori())
                .tarih(LocalDateTime.now())
                .goruntulenmeSayisi(0)
                .build();

        repo.save(e);

        return mapper.toResponse(e);
    }

    @Override
    public List<EgitimResponse> tumEgitimler() {
        return repo.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<EgitimResponse> kategoriyeGore(String kategori) {
        return repo.findByKategori(kategori)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<EgitimResponse> diyetisyenEgitimleri(Long diyetisyenId) {
        return repo.findByDiyetisyenId(diyetisyenId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public EgitimResponse egitimGetir(Long id) {
        Egitim e = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Eğitim bulunamadı"));

        return mapper.toResponse(e);
    }

    @Override
    public EgitimResponse goruntule(Long id) {

        Egitim e = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Eğitim bulunamadı"));

        e.setGoruntulenmeSayisi(e.getGoruntulenmeSayisi() + 1);
        repo.save(e);

        return mapper.toResponse(e);
    }
}

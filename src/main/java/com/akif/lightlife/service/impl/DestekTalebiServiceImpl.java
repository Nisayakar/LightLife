package com.akif.lightlife.service.impl;

import com.akif.lightlife.dto.request.DestekTalebiOlusturRequest;
import com.akif.lightlife.dto.response.DestekTalebiResponse;
import com.akif.lightlife.entity.DestekTalebi;
import com.akif.lightlife.exception.NotFoundException;
import com.akif.lightlife.mapper.DestekTalebiMapper;
import com.akif.lightlife.repository.DestekTalebiRepository;
import com.akif.lightlife.service.DestekTalebiService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DestekTalebiServiceImpl implements DestekTalebiService {

    private final DestekTalebiRepository repo;
    private final DestekTalebiMapper mapper;

    @Override
    public DestekTalebiResponse talepOlustur(DestekTalebiOlusturRequest r) {

        DestekTalebi talep = DestekTalebi.builder()
                .kullaniciId(r.getKullaniciId())
                .konu(r.getKonu())
                .mesaj(r.getMesaj())
                .tarih(LocalDateTime.now())
                .durum(DestekTalebi.TalepDurumu.ACIK)
                .build();

        repo.save(talep);

        return mapper.toResponse(talep);
    }

    @Override
    public List<DestekTalebiResponse> kullaniciTalepleri(Long kullaniciId) {
        return repo.findByKullaniciId(kullaniciId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public DestekTalebiResponse talepDurumDegistir(Long talepId, String yeniDurum) {

        DestekTalebi talep = repo.findById(talepId)
                .orElseThrow(() -> new NotFoundException("Destek talebi bulunamadı"));

        talep.setDurum(DestekTalebi.TalepDurumu.valueOf(yeniDurum));
        repo.save(talep);

        return mapper.toResponse(talep);
    }
}

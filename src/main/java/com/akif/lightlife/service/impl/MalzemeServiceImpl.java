package com.akif.lightlife.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.akif.lightlife.dto.request.MalzemeRequest;
import com.akif.lightlife.dto.response.MalzemeResponse;
import com.akif.lightlife.entity.DiyetTarifi;
import com.akif.lightlife.entity.Malzeme;
import com.akif.lightlife.entity.Yiyecek;
import com.akif.lightlife.mapper.MalzemeMapper;
import com.akif.lightlife.repository.DiyetTarifiRepository;
import com.akif.lightlife.repository.MalzemeRepository;
import com.akif.lightlife.repository.YiyecekRepository;
import com.akif.lightlife.service.MalzemeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MalzemeServiceImpl implements MalzemeService {

    private final MalzemeRepository malzemeRepo;
    private final YiyecekRepository yiyecekRepo;
    private final DiyetTarifiRepository tarifRepo;
    private final MalzemeMapper mapper;

    @Override
    public MalzemeResponse ekle(Long tarifId, MalzemeRequest r) {

        DiyetTarifi tarif = tarifRepo.findById(tarifId)
                .orElseThrow(() -> new RuntimeException("Tarif bulunamadı"));

        Yiyecek yiyecek = yiyecekRepo.findById(r.getYiyecekId())
                .orElseThrow(() -> new RuntimeException("Yiyecek bulunamadı"));

        Malzeme m = Malzeme.builder()
                .gram(r.getGram())
                .yiyecek(yiyecek)
                .tarif(tarif)
                .build();

        malzemeRepo.save(m);

        return mapper.toResponse(m);
    }

    @Override
    public List<MalzemeResponse> tarifMalzemeleri(Long tarifId) {
        DiyetTarifi tarif = tarifRepo.findById(tarifId)
                .orElseThrow(() -> new RuntimeException("Tarif bulunamadı"));

        return malzemeRepo.findByTarif(tarif)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public void sil(Long malzemeId) {
        if (!malzemeRepo.existsById(malzemeId)) {
            throw new RuntimeException("Malzeme bulunamadı");
        }
        malzemeRepo.deleteById(malzemeId);
    }
}

package com.akif.lightlife.service.impl;

import com.akif.lightlife.dto.request.OgunTarifEkleRequest;
import com.akif.lightlife.dto.response.OgunTarifResponse;
import com.akif.lightlife.entity.DiyetTarifi;
import com.akif.lightlife.entity.Ogun;
import com.akif.lightlife.entity.OgunDiyetTarifi;
import com.akif.lightlife.exception.NotFoundException;
import com.akif.lightlife.mapper.OgunDiyetTarifiMapper;
import com.akif.lightlife.repository.DiyetTarifiRepository;
import com.akif.lightlife.repository.OgunDiyetTarifiRepository;
import com.akif.lightlife.repository.OgunRepository;
import com.akif.lightlife.service.OgunDiyetTarifiService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OgunDiyetTarifiServiceImpl implements OgunDiyetTarifiService {

    private final OgunRepository ogunRepo;
    private final DiyetTarifiRepository tarifRepo;
    private final OgunDiyetTarifiRepository repo;
    private final OgunDiyetTarifiMapper mapper;

    @Override
    public OgunTarifResponse ekle(OgunTarifEkleRequest r) {

        Ogun ogun = ogunRepo.findById(r.getOgunId())
                .orElseThrow(() -> new NotFoundException("Öğün bulunamadı"));

        DiyetTarifi tarif = tarifRepo.findById(r.getTarifId())
                .orElseThrow(() -> new NotFoundException("Tarif bulunamadı"));

        OgunDiyetTarifi odt = OgunDiyetTarifi.builder()
                .ogun(ogun)
                .tarif(tarif)
                .porsiyon(r.getPorsiyon())
                .build();

        repo.save(odt);

        return mapper.toResponse(odt);
    }

    @Override
    public List<OgunTarifResponse> ogunTarifleri(Long ogunId) {

        Ogun ogun = ogunRepo.findById(ogunId)
                .orElseThrow(() -> new NotFoundException("Öğün bulunamadı"));

        return repo.findByOgun(ogun)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public void sil(Long id) {

        if (!repo.existsById(id)) {
            throw new NotFoundException("Öğe bulunamadı");
        }

        repo.deleteById(id);
    }
}

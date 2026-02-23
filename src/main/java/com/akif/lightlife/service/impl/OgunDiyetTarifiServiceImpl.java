package com.akif.lightlife.service.impl;

import com.akif.lightlife.dto.request.OgunDiyetTarifiEkleRequest;
import com.akif.lightlife.dto.response.OgunDiyetTarifiResponse;
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

    private final OgunDiyetTarifiRepository ogunDiyetTarifiRepository;
    private final OgunRepository ogunRepository;
    private final DiyetTarifiRepository diyetTarifiRepository;
    private final OgunDiyetTarifiMapper ogunDiyetTarifiMapper;

    @Override
    public OgunDiyetTarifiResponse tarifEkle(OgunDiyetTarifiEkleRequest request) {

        Ogun ogun = ogunRepository.findById(request.getOgunId())
                .orElseThrow(() -> new NotFoundException("Öğün bulunamadı: " + request.getOgunId()));

        DiyetTarifi tarif = diyetTarifiRepository.findById(request.getTarifId())
                .orElseThrow(() -> new NotFoundException("Diyet tarifi bulunamadı: " + request.getTarifId()));

        OgunDiyetTarifi ogunDiyetTarifi = OgunDiyetTarifi.builder()
                .ogun(ogun)
                .tarif(tarif)
                .porsiyon(request.getPorsiyon())
                .build();

        ogunDiyetTarifiRepository.save(ogunDiyetTarifi);

        return ogunDiyetTarifiMapper.toResponse(ogunDiyetTarifi);
    }

    @Override
    public List<OgunDiyetTarifiResponse> ogunTarifleri(Long ogunId) {
        List<OgunDiyetTarifi> tarifler = ogunDiyetTarifiRepository.findByOgunId(ogunId);
        return tarifler.stream()
                .map(ogunDiyetTarifiMapper::toResponse)
                .toList();
    }
}

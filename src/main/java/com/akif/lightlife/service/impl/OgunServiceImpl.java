package com.akif.lightlife.service.impl;

import com.akif.lightlife.dto.request.OgunKayitRequest;
import com.akif.lightlife.dto.request.OgunTarifEkleRequest;
import com.akif.lightlife.dto.response.OgunResponse;

import com.akif.lightlife.entity.DiyetTarifi;
import com.akif.lightlife.entity.Kullanici;
import com.akif.lightlife.entity.Ogun;
import com.akif.lightlife.entity.OgunDiyetTarifi;

import com.akif.lightlife.exception.NotFoundException;
import com.akif.lightlife.mapper.OgunMapper;
import com.akif.lightlife.repository.DiyetTarifiRepository;
import com.akif.lightlife.repository.KullaniciRepository;
import com.akif.lightlife.repository.OgunDiyetTarifiRepository;
import com.akif.lightlife.repository.OgunRepository;
import com.akif.lightlife.service.OgunService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OgunServiceImpl implements OgunService {

    private final OgunRepository ogunRepo;
    private final KullaniciRepository kullaniciRepo;
    private final DiyetTarifiRepository tarifRepo;
    private final OgunDiyetTarifiRepository odtRepo;
    private final OgunMapper mapper;

    @Override
    public OgunResponse ogunEkle(OgunKayitRequest r) {

        Kullanici k = kullaniciRepo.findById(r.getKullaniciId())
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));

        Ogun o = Ogun.builder()
                .kullanici(k)
                .tip(r.getTip())
                .tarih(LocalDate.now())
                .build();

        ogunRepo.save(o);

        return mapper.toResponse(o);
    }

    @Override
    public OgunResponse oguneTarifEkle(OgunTarifEkleRequest r) {

        Ogun ogun = ogunRepo.findById(r.getOgunId())
                .orElseThrow(() -> new NotFoundException("Öğün bulunamadı"));

        DiyetTarifi tarif = tarifRepo.findById(r.getTarifId())
                .orElseThrow(() -> new NotFoundException("Tarif bulunamadı"));

        OgunDiyetTarifi odt = OgunDiyetTarifi.builder()
                .ogun(ogun)
                .tarif(tarif)
                .porsiyon(r.getPorsiyon())
                .build();

        odtRepo.save(odt);

        return mapper.toResponse(ogun);
    }

    @Override
    public OgunResponse ogunGetir(Long id) {
        return mapper.toResponse(
                ogunRepo.findById(id)
                        .orElseThrow(() -> new NotFoundException("Öğün bulunamadı"))
        );
    }

    @Override
    public List<OgunResponse> kullanicininGunlukOgunleri(Long kullaniciId) {

        Kullanici k = kullaniciRepo.findById(kullaniciId)
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));

        return ogunRepo.findByKullaniciAndTarih(k, LocalDate.now())
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}

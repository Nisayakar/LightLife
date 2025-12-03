package com.akif.lightlife.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.akif.lightlife.dto.request.MalzemeRequest;
import com.akif.lightlife.dto.request.TarifOlusturRequest;
import com.akif.lightlife.dto.response.TarifResponse;
import com.akif.lightlife.entity.DiyetTarifi;
import com.akif.lightlife.entity.Diyetisyen;
import com.akif.lightlife.entity.Malzeme;
import com.akif.lightlife.exception.NotFoundException;
import com.akif.lightlife.mapper.DiyetTarifiMapper;
import com.akif.lightlife.repository.DiyetTarifiRepository;
import com.akif.lightlife.repository.DiyetisyenRepository;
import com.akif.lightlife.repository.MalzemeRepository;
import com.akif.lightlife.repository.YiyecekRepository;
import com.akif.lightlife.service.DiyetTarifiService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiyetTarifiServiceImpl implements DiyetTarifiService {

    private final DiyetTarifiRepository tarifRepo;
    private final MalzemeRepository malzemeRepo;
    private final YiyecekRepository yiyecekRepo;
    private final DiyetisyenRepository diyetisyenRepo;
    private final DiyetTarifiMapper mapper;

    @Override
    public TarifResponse tarifOlustur(TarifOlusturRequest r) {

        Diyetisyen d = diyetisyenRepo.findById(r.getDiyetisyenId())
                .orElseThrow(() -> new NotFoundException("Diyetisyen bulunamadı"));

        DiyetTarifi tarif = DiyetTarifi.builder()
                .ad(r.getAd())
                .aciklama(r.getAciklama())
                .diyetisyen(d)
                .build();

        tarifRepo.save(tarif);

        int toplamKalori = 0;

        for (MalzemeRequest mr : r.getMalzemeler()) {

            var yiyecek = yiyecekRepo.findById(mr.getYiyecekId())
                    .orElseThrow(() -> new NotFoundException("Yiyecek bulunamadı"));

            Malzeme m = Malzeme.builder()
                    .gram(mr.getGram())
                    .yiyecek(yiyecek)
                    .tarif(tarif)
                    .build();

            malzemeRepo.save(m);

            toplamKalori += (yiyecek.getKalori() / 100.0) * mr.getGram();
        }

        tarif.setToplamKalori(toplamKalori);
        tarifRepo.save(tarif);

        return mapper.toResponse(tarif);
    }

    @Override
    public TarifResponse tarifGetir(Long id) {
        return tarifRepo.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new NotFoundException("Tarif bulunamadı"));
    }

    @Override
    public List<TarifResponse> tarifListele() {
        return tarifRepo.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}

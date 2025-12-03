package com.akif.lightlife.service.impl;

import com.akif.lightlife.dto.request.OdemeYapRequest;
import com.akif.lightlife.dto.response.OdemeResponse;
import com.akif.lightlife.entity.Abonelik;
import com.akif.lightlife.entity.Odeme;
import com.akif.lightlife.exception.NotFoundException;
import com.akif.lightlife.mapper.OdemeMapper;
import com.akif.lightlife.repository.AbonelikRepository;
import com.akif.lightlife.repository.OdemeRepository;
import com.akif.lightlife.service.OdemeService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OdemeServiceImpl implements OdemeService {

    private final OdemeRepository odemeRepo;
    private final AbonelikRepository abonelikRepo;
    private final OdemeMapper mapper;

    @Override
    public OdemeResponse odemeYap(OdemeYapRequest r) {

        Abonelik abonelik = abonelikRepo.findById(r.getAbonelikId())
                .orElseThrow(() -> new NotFoundException("Abonelik bulunamadı"));

        Odeme odeme = Odeme.builder()
                .abonelik(abonelik)
                .tutar(r.getTutar())
                .odemeYontemi(r.getOdemeYontemi())
                .durum(Odeme.OdemeDurumu.BASARILI)
                .tarih(LocalDateTime.now())
                .islemNo(UUID.randomUUID().toString())
                .build();

        odemeRepo.save(odeme);

        abonelik.setAktifMi(true);
        abonelikRepo.save(abonelik);

        return mapper.toResponse(odeme);
    }

    @Override
    public List<OdemeResponse> abonelikOdemeleri(Long abonelikId) {

        Abonelik abonelik = abonelikRepo.findById(abonelikId)
                .orElseThrow(() -> new NotFoundException("Abonelik bulunamadı"));

        return odemeRepo.findByAbonelik(abonelik)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}

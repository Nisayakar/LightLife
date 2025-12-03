package com.akif.lightlife.service.impl;

import com.akif.lightlife.dto.request.MesajGonderRequest;
import com.akif.lightlife.dto.response.MesajResponse;
import com.akif.lightlife.entity.Mesaj;
import com.akif.lightlife.exception.BadRequestException;
import com.akif.lightlife.exception.NotFoundException;
import com.akif.lightlife.mapper.MesajMapper;
import com.akif.lightlife.repository.MesajRepository;
import com.akif.lightlife.service.MesajService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MesajServiceImpl implements MesajService {

    private final MesajRepository repo;
    private final MesajMapper mapper;

    @Override
    public MesajResponse mesajGonder(MesajGonderRequest r) {

        if (r.getIcerik() == null || r.getIcerik().isBlank()) {
            throw new BadRequestException("Mesaj içeriği boş olamaz");
        }

        Mesaj m = Mesaj.builder()
                .gonderenId(r.getGonderenId())
                .aliciId(r.getAliciId())
                .icerik(r.getIcerik())
                .tarih(LocalDateTime.now())
                .okunduMu(false)
                .build();

        repo.save(m);

        return mapper.toResponse(m);
    }

    @Override
    public List<MesajResponse> sohbetGetir(Long kullaniciId, Long diyetisyenId) {

        List<Mesaj> mesajlar = repo.sohbetGetir(kullaniciId, diyetisyenId);

        if (mesajlar.isEmpty()) {
            throw new NotFoundException("Mesaj geçmişi bulunamadı");
        }

        return mesajlar.stream()
                .map(mapper::toResponse)
                .toList();
    }
}

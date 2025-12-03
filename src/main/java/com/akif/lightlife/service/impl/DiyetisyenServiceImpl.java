package com.akif.lightlife.service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.akif.lightlife.dto.request.DiyetisyenGirisRequest;
import com.akif.lightlife.dto.request.DiyetisyenKayitRequest;
import com.akif.lightlife.dto.response.DiyetisyenResponse;
import com.akif.lightlife.entity.Diyetisyen;
import com.akif.lightlife.exception.NotFoundException;
import com.akif.lightlife.exception.AlreadyExistsException;
import com.akif.lightlife.mapper.DiyetisyenMapper;
import com.akif.lightlife.repository.DiyetisyenRepository;
import com.akif.lightlife.service.DiyetisyenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiyetisyenServiceImpl implements DiyetisyenService {

    private final DiyetisyenRepository repository;
    private final DiyetisyenMapper mapper;
    private final PasswordEncoder encoder;

    @Override
    public DiyetisyenResponse kayit(DiyetisyenKayitRequest r) {

        if (repository.existsByEmail(r.getEmail())) {
            throw new AlreadyExistsException("Bu email zaten kayıtlı.");
        }

        Diyetisyen d = Diyetisyen.builder()
                .ad(r.getAd())
                .soyad(r.getSoyad())
                .email(r.getEmail())
                .telefon(r.getTelefon())
                .uzmanlik(r.getUzmanlik())
                .deneyimYili(r.getDeneyimYili())
                .sifre(encoder.encode(r.getSifre()))
                .build();

        repository.save(d);

        return mapper.toResponse(d);
    }

    @Override
    public DiyetisyenResponse giris(DiyetisyenGirisRequest r) {

        Diyetisyen d = repository.findByEmail(r.getEmail())
                .orElseThrow(() -> new NotFoundException("Diyetisyen bulunamadı"));

        if (!encoder.matches(r.getSifre(), d.getSifre())) {
            throw new NotFoundException("Hatalı şifre!");
        }

        return mapper.toResponse(d);
    }

    @Override
    public DiyetisyenResponse getById(long id) {
        return mapper.toResponse(
                repository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Diyetisyen bulunamadı"))
        );
    }

    @Override
    public List<DiyetisyenResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}

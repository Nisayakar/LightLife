package com.akif.lightlife.service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.akif.lightlife.dto.request.KullaniciGirisRequest;
import com.akif.lightlife.dto.request.KullaniciKayitRequest;
import com.akif.lightlife.dto.response.KullaniciResponse;
import com.akif.lightlife.entity.Kullanici;
import com.akif.lightlife.exception.NotFoundException;
import com.akif.lightlife.exception.AlreadyExistsException;
import com.akif.lightlife.mapper.KullaniciMapper;
import com.akif.lightlife.repository.KullaniciRepository;
import com.akif.lightlife.service.KullaniciService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KullaniciServiceImpl implements KullaniciService {

    private final KullaniciRepository repository;
    private final KullaniciMapper mapper;
    private final PasswordEncoder encoder;

    @Override
    public KullaniciResponse kayit(KullaniciKayitRequest r) {

        if (repository.existsByEmail(r.getEmail())) {
            throw new AlreadyExistsException("Bu email zaten kullanılıyor.");
        }

        Kullanici k = Kullanici.builder()
                .ad(r.getAd())
                .soyad(r.getSoyad())
                .email(r.getEmail())
                .telefon(r.getTelefon())
                .rol("Kullanici")
                .sifre(encoder.encode(r.getSifre()))
                .build();

        repository.save(k);

        return mapper.toResponse(k);
    }

    @Override
    public KullaniciResponse getById(Long id) {
        Kullanici k = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));

        return mapper.toResponse(k);
    }

    @Override
    public List<KullaniciResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public KullaniciResponse giris(KullaniciGirisRequest request) {

        Kullanici k = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));

        if (!encoder.matches(request.getSifre(), k.getSifre())) {
            throw new NotFoundException("Hatalı şifre!");
        }

        return mapper.toResponse(k);
    }
}

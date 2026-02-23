package com.akif.lightlife.service.impl;

import com.akif.lightlife.dto.request.DiyetisyenGirisRequest;
import com.akif.lightlife.dto.request.DiyetisyenKayitRequest;
import com.akif.lightlife.dto.request.DiyetisyenProfilGuncelleRequest;
import com.akif.lightlife.dto.response.DiyetisyenResponse;
import com.akif.lightlife.dto.response.KullaniciResponse;
import com.akif.lightlife.entity.Diyetisyen;
import com.akif.lightlife.entity.Kullanici;
import com.akif.lightlife.exception.AlreadyExistsException;
import com.akif.lightlife.exception.NotFoundException;
import com.akif.lightlife.mapper.DiyetisyenMapper;
import com.akif.lightlife.mapper.KullaniciMapper;
import com.akif.lightlife.repository.DiyetisyenRepository;
import com.akif.lightlife.repository.KullaniciRepository;
import com.akif.lightlife.service.DiyetisyenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiyetisyenServiceImpl implements DiyetisyenService {

    private final DiyetisyenRepository repo;
    private final KullaniciRepository kullaniciRepository;
    private final DiyetisyenMapper mapper;
    private final KullaniciMapper kullaniciMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public DiyetisyenResponse kayit(DiyetisyenKayitRequest r) {
        if (repo.existsByEmail(r.getEmail())) {
            throw new AlreadyExistsException("Bu email ile kayıtlı bir diyetisyen zaten mevcut");
        }

        Diyetisyen d = Diyetisyen.builder()
                .ad(r.getAd())
                .soyad(r.getSoyad())
                .email(r.getEmail().trim().toLowerCase())
                .telefon(r.getTelefon())
                .uzmanlik(r.getUzmanlik())
                .deneyimYili(r.getDeneyimYili())
                .sifre(passwordEncoder.encode(r.getSifre()))
                .build();

        d = repo.save(d);
        return mapper.toResponse(d);
    }

    @Override
    public DiyetisyenResponse giris(DiyetisyenGirisRequest r) {
        Diyetisyen d = repo.findByEmail(r.getEmail())
                .orElseThrow(() -> new NotFoundException("Email veya şifre hatalı"));

        if (!passwordEncoder.matches(r.getSifre(), d.getSifre())) {
            throw new NotFoundException("Email veya şifre hatalı");
        }

        return mapper.toResponse(d);
    }

    @Override
    public List<DiyetisyenResponse> getAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public DiyetisyenResponse getById(Long id) {
        Diyetisyen d = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Diyetisyen bulunamadı"));
        return mapper.toResponse(d);
    }

    @Override
    @Transactional
    public DiyetisyenResponse profilGuncelle(Long id, DiyetisyenProfilGuncelleRequest r) {
        Diyetisyen d = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Diyetisyen bulunamadı"));

        if (r.getAd() != null && !r.getAd().isBlank()) d.setAd(r.getAd());
        if (r.getSoyad() != null && !r.getSoyad().isBlank()) d.setSoyad(r.getSoyad());
        if (r.getTelefon() != null && !r.getTelefon().isBlank()) d.setTelefon(r.getTelefon());
        if (r.getUzmanlik() != null && !r.getUzmanlik().isBlank()) d.setUzmanlik(r.getUzmanlik());
        if (r.getDeneyimYili() != null && r.getDeneyimYili() >= 0) d.setDeneyimYili(r.getDeneyimYili());

        d = repo.save(d);
        return mapper.toResponse(d);
    }

    @Override
    public List<KullaniciResponse> getKullanicilar(Long diyetisyenId) {
        Diyetisyen d = repo.findById(diyetisyenId)
                .orElseThrow(() -> new NotFoundException("Diyetisyen bulunamadı"));

        List<Kullanici> list = kullaniciRepository.findByDiyetisyen(d);

        return list.stream()
                .map(kullaniciMapper::toResponse)
                .toList();
    }

    // ====================== ŞİFRE GÜNCELLEME (YENİ) ======================

    @Override
    @Transactional
    public void sifreDegistir(Long id, String eskiSifre, String yeniSifre) {
        // 1. Diyetisyeni bul
        Diyetisyen d = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Diyetisyen bulunamadı"));

        // 2. Mevcut şifreyi BCrypt ile doğrula
        if (!passwordEncoder.matches(eskiSifre, d.getSifre())) {
            throw new RuntimeException("Mevcut şifreniz hatalı. Lütfen kontrol ediniz.");
        }

        // 3. Yeni şifreyi encode et ve kaydet
        d.setSifre(passwordEncoder.encode(yeniSifre));
        repo.save(d);
    }
}
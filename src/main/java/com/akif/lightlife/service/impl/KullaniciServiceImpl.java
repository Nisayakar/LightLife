package com.akif.lightlife.service.impl;

import com.akif.lightlife.dto.request.KullaniciGirisRequest;
import com.akif.lightlife.dto.request.KullaniciKayitRequest;
import com.akif.lightlife.dto.request.KullaniciProfilGuncelleRequest;
import com.akif.lightlife.dto.response.KullaniciResponse;
import com.akif.lightlife.entity.Diyetisyen;
import com.akif.lightlife.entity.Kullanici;
import com.akif.lightlife.exception.AlreadyExistsException;
import com.akif.lightlife.exception.BadRequestException;
import com.akif.lightlife.exception.NotFoundException;
import com.akif.lightlife.mapper.KullaniciMapper;
import com.akif.lightlife.repository.DiyetisyenRepository;
import com.akif.lightlife.repository.KullaniciRepository;
import com.akif.lightlife.service.KullaniciService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KullaniciServiceImpl implements KullaniciService {

    private final KullaniciRepository repo;
    private final DiyetisyenRepository diyetisyenRepository;
    private final KullaniciMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public KullaniciResponse kayit(KullaniciKayitRequest r) {

        if (repo.existsByEmail(r.getEmail())) {
            throw new AlreadyExistsException("Bu email ile zaten bir kullanıcı mevcut");
        }

        Kullanici k = Kullanici.builder()
                .ad(r.getAd())
                .soyad(r.getSoyad())
                .email(r.getEmail())
                .telefon(r.getTelefon())
                .sifre(passwordEncoder.encode(r.getSifre()))
                .rol("KULLANICI") // İstersen enum'a taşıyabilirsin
                .build();

        k = repo.save(k);
        return mapper.toResponse(k);
    }

    @Override
    public KullaniciResponse giris(KullaniciGirisRequest r) {

        Kullanici k = repo.findByEmail(r.getEmail())
                .orElseThrow(() -> new NotFoundException("Email veya şifre hatalı"));

        if (!passwordEncoder.matches(r.getSifre(), k.getSifre())) {
            throw new NotFoundException("Email veya şifre hatalı");
        }

        return mapper.toResponse(k);
    }

    @Override
    public List<KullaniciResponse> getAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public KullaniciResponse getById(Long id) {
        Kullanici k = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));
        return mapper.toResponse(k);
    }

    @Override
    public KullaniciResponse profilGuncelle(Long id, KullaniciProfilGuncelleRequest r) {
        Kullanici k = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));

        // Temel bilgiler
        if (r.getYas() != null) k.setYas(r.getYas());
        if (r.getBoyCm() != null) k.setBoyCm(r.getBoyCm());
        if (r.getKiloKg() != null) k.setKiloKg(r.getKiloKg());
        if (r.getHedefKilo() != null) k.setHedefKilo(r.getHedefKilo());
        if (r.getCinsiyet() != null) k.setCinsiyet(r.getCinsiyet());
        if (r.getSaglikNotu() != null) k.setSaglikNotu(r.getSaglikNotu());
        
        // Hedef Kalori Bilgisi (JavaScript'ten gelecek olan)
        if (r.getGunlukKaloriHedefi() != null) {
            k.setGunlukKaloriHedefi(r.getGunlukKaloriHedefi());
        }

        k = repo.save(k); // Değişiklikleri kalıcı hale getirir
        return mapper.toResponse(k);
    }

    @Override
    public void sifreGuncelle(Long id, String eskiSifre, String yeniSifre) {
        Kullanici kullanici = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));


        if (!passwordEncoder.matches(eskiSifre, kullanici.getSifre())) {
            throw new BadRequestException("Eski şifre yanlış");
        }


        kullanici.setSifre(passwordEncoder.encode(yeniSifre));
        repo.save(kullanici);
    }

    @Override
    public void diyetisyenAta(Long kullaniciId, Long diyetisyenId) {
        Kullanici k = repo.findById(kullaniciId)
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));

        Diyetisyen d = diyetisyenRepository.findById(diyetisyenId)
                .orElseThrow(() -> new NotFoundException("Diyetisyen bulunamadı"));

        k.setDiyetisyen(d);
        repo.save(k);
    }
}

package com.akif.lightlife.service.impl;

import org.springframework.stereotype.Service;

import com.akif.lightlife.entity.Diyetisyen;
import com.akif.lightlife.entity.Kullanici;
import com.akif.lightlife.entity.KullaniciDiyetisyen;
import com.akif.lightlife.exception.NotFoundException;
import com.akif.lightlife.repository.DiyetisyenRepository;
import com.akif.lightlife.repository.KullaniciDiyetisyenRepository;
import com.akif.lightlife.repository.KullaniciRepository;
import com.akif.lightlife.service.KullanıcıDiyetisyenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KullaniciDiyetisyenServiceImpl implements KullanıcıDiyetisyenService {

    private final KullaniciRepository kullaniciRepository;
    private final DiyetisyenRepository diyetisyenRepository;
    private final KullaniciDiyetisyenRepository repository;

    @Override
    public KullaniciDiyetisyen ata(Long kullaniciId, Long diyetisyenId) {

        Kullanici kullanici = kullaniciRepository.findById(kullaniciId)
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));

        Diyetisyen diyetisyen = diyetisyenRepository.findById(diyetisyenId)
                .orElseThrow(() -> new NotFoundException("Diyetisyen bulunamadı"));

        KullaniciDiyetisyen aktif = repository.findByKullaniciAndAktifTrue(kullanici);
        if (aktif != null) {
            aktif.setAktif(false);
            repository.save(aktif);
        }


        KullaniciDiyetisyen yeni = KullaniciDiyetisyen.builder()
                .kullanici(kullanici)
                .diyetisyen(diyetisyen)
                .aktif(true)
                .build();

        return repository.save(yeni);
    }

    @Override
    public KullaniciDiyetisyen aktifDiyetisyen(Long kullaniciId) {

        Kullanici kullanici = kullaniciRepository.findById(kullaniciId)
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));

        return repository.findByKullaniciAndAktifTrue(kullanici);
    }

    @Override
    public void diyetisyenDegistir(Long kullaniciId, Long yeniDiyetisyenId) {
        ata(kullaniciId, yeniDiyetisyenId);
    }
}

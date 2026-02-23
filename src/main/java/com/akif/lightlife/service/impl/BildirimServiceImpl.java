package com.akif.lightlife.service.impl;

import com.akif.lightlife.dto.request.BildirimOlusturRequest;
import com.akif.lightlife.dto.response.BildirimResponse;
import com.akif.lightlife.entity.Bildirim;
import com.akif.lightlife.exception.NotFoundException;
import com.akif.lightlife.mapper.BildirimMapper;
import com.akif.lightlife.pattern.observer.BildirimMerkezi;
import com.akif.lightlife.pattern.observer.BildirimOlayi;
import com.akif.lightlife.repository.BildirimRepository;
import com.akif.lightlife.service.BildirimService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.akif.lightlife.enums.BildirimHedefTipi;


import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BildirimServiceImpl implements BildirimService {

    private final BildirimRepository repo;
    private final BildirimMapper mapper;
    private final BildirimMerkezi bildirimMerkezi;

    @Override
    public BildirimResponse gonder(BildirimOlusturRequest r) {

       
        Bildirim b = Bildirim.builder()
                .kullaniciId(r.getKullaniciId())
                .baslik(r.getBaslik())
                .mesaj(r.getMesaj())
                .tip(r.getTip())
                .okunduMu(false)
                .tarih(LocalDateTime.now())
                .build();

        repo.save(b);

      
        BildirimOlayi olay = new BildirimOlayi(
                r.getKullaniciId(),
                r.getBaslik(),
                r.getMesaj(),
                BildirimHedefTipi.KULLANICI
        );


        bildirimMerkezi.bildir(olay);

        return mapper.toResponse(b);
    }

    @Override
    public List<BildirimResponse> kullaniciBildirimleri(Long kullaniciId) {
        return repo.findByKullaniciId(kullaniciId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<BildirimResponse> okunmamisBildirimler(Long kullaniciId) {
        return repo.findByKullaniciIdAndOkunduMuFalse(kullaniciId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public BildirimResponse okunduYap(Long id) {

        Bildirim b = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Bildirim bulunamadı"));

        b.setOkunduMu(true);
        repo.save(b);

        return mapper.toResponse(b);
    }
    
    @Override
    @Transactional
    public void tumunuOkunduYap(Long kullaniciId) {
        List<Bildirim> list = repo.findByKullaniciIdAndOkunduMuFalse(kullaniciId);
        list.forEach(b -> b.setOkunduMu(true));
        repo.saveAll(list);
    }

}

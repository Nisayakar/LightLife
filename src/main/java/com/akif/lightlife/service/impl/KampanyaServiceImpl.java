package com.akif.lightlife.service.impl;

import com.akif.lightlife.dto.request.KampanyaOlusturRequest;
import com.akif.lightlife.dto.response.KampanyaResponse;
import com.akif.lightlife.entity.Kampanya;
import com.akif.lightlife.exception.NotFoundException;
import com.akif.lightlife.mapper.KampanyaMapper;
import com.akif.lightlife.pattern.strategy.kampanya.KampanyaStratejiSecici;
import com.akif.lightlife.pattern.strategy.kampanya.KampanyaStratejisi;
import com.akif.lightlife.repository.KampanyaRepository;
import com.akif.lightlife.service.KampanyaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KampanyaServiceImpl implements KampanyaService {

    private final KampanyaRepository repo;
    private final KampanyaMapper mapper;

    @Override
    public KampanyaResponse olustur(KampanyaOlusturRequest r) {

        Kampanya k = Kampanya.builder()
                .diyetisyenId(r.getDiyetisyenId())
                .baslik(r.getBaslik())
                .aciklama(r.getAciklama())
                .indirimYuzdesi(r.getIndirimYuzdesi())
                .baslangicTarihi(LocalDate.parse(r.getBaslangicTarihi()))
                .bitisTarihi(LocalDate.parse(r.getBitisTarihi()))
                .aktifMi(true)
                .kategori(r.getKategori())
                .build();

        repo.save(k);
        return mapper.toResponse(k);
    }

    @Override
    public List<KampanyaResponse> tumKampanyalar() {
        return repo.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<KampanyaResponse> aktifKampanyalar() {
        return repo.findByAktifMiTrue()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<KampanyaResponse> kategoriyeGore(String kategori) {
        return repo.findByKategori(kategori)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<KampanyaResponse> diyetisyenKampanyalari(Long diyetisyenId) {
        return repo.findByDiyetisyenId(diyetisyenId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public KampanyaResponse kampanyaGetir(Long id) {
        Kampanya k = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Kampanya bulunamadı"));
        return mapper.toResponse(k);
    }

    @Override
    public KampanyaResponse durumDegistir(Long id, boolean aktifMi) {
        Kampanya k = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Kampanya bulunamadı"));

        k.setAktifMi(aktifMi);
        repo.save(k);

        return mapper.toResponse(k);
    }

    @Override
    public void sil(Long id) {
        repo.deleteById(id);
    }

    @Override
    public double indirimliTutarHesapla(Long kampanyaId, double tutar) {

        Kampanya k = repo.findById(kampanyaId)
                .orElseThrow(() -> new NotFoundException("Kampanya bulunamadı"));

        if (!k.isAktifMi() || !tarihUygunMu(k)) {
            return tutar;
        }

        KampanyaStratejisi strateji = KampanyaStratejiSecici.stratejiSec(k);
        return strateji.indirimUygula(tutar);
    }

    private boolean tarihUygunMu(Kampanya k) {
        LocalDate today = LocalDate.now();
        return !today.isBefore(k.getBaslangicTarihi())
                && !today.isAfter(k.getBitisTarihi());
    }
}

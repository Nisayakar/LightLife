package com.akif.lightlife.controller;

import com.akif.lightlife.dto.request.TarifOlusturRequest;
import com.akif.lightlife.dto.response.DiyetTarifiSearchResponse;
import com.akif.lightlife.dto.response.TarifResponse;
import com.akif.lightlife.entity.DiyetTarifi;
import com.akif.lightlife.repository.DiyetTarifiRepository;
import com.akif.lightlife.service.DiyetTarifiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tarif")
@RequiredArgsConstructor
public class DiyetTarifiController {

    private final DiyetTarifiRepository diyetTarifiRepository;
    private final DiyetTarifiService diyetTarifiService;

    // 1) Tüm tarifleri listele (Diyetisyen panelindeki "Tarif Listele" butonu burayı kullanabilir)
    @GetMapping
    public ResponseEntity<List<DiyetTarifiSearchResponse>> tumTarifler() {
        List<DiyetTarifi> tarifler = diyetTarifiRepository.findAll();

        List<DiyetTarifiSearchResponse> resp = tarifler.stream()
                .map(t -> DiyetTarifiSearchResponse.builder()
                        .id(t.getId())
                        .ad(t.getAd())
                        .aciklama(t.getAciklama())
                        .diyetisyenId(
                                t.getDiyetisyen() != null ? t.getDiyetisyen().getId() : null
                        )
                        .toplamKalori(t.getToplamKalori())
                        .build()
                )
                .toList();

        return ResponseEntity.ok(resp);
    }

    // 2) İsimle arama (Kullanıcı panelindeki "Tarif Ara" burayı çağırmalı: /api/tarif/ara?q=...)
    @GetMapping("/ara")
    public ResponseEntity<List<DiyetTarifiSearchResponse>> ara(@RequestParam String q) {
        List<DiyetTarifi> tarifler =
                diyetTarifiRepository.findTop10ByAdContainingIgnoreCaseOrderByAdAsc(q);

        List<DiyetTarifiSearchResponse> resp = tarifler.stream()
                .map(t -> DiyetTarifiSearchResponse.builder()
                        .id(t.getId())
                        .ad(t.getAd())
                        .aciklama(t.getAciklama())
                        .diyetisyenId(
                                t.getDiyetisyen() != null ? t.getDiyetisyen().getId() : null
                        )
                        .toplamKalori(t.getToplamKalori())
                        .build()
                )
                .toList();

        return ResponseEntity.ok(resp);
    }

    // 3) Tarif oluştur (Diyetisyen panelindeki "Tarif Oluştur" butonu buraya POST atacak)
    @PostMapping("/olustur")
    public ResponseEntity<TarifResponse> olustur(@RequestBody TarifOlusturRequest request) {
        TarifResponse response = diyetTarifiService.tarifOlustur(request);
        return ResponseEntity.ok(response);
    }
}

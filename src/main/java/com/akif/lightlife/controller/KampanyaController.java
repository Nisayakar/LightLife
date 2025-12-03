package com.akif.lightlife.controller;

import com.akif.lightlife.dto.request.KampanyaOlusturRequest;
import com.akif.lightlife.dto.response.KampanyaResponse;
import com.akif.lightlife.service.KampanyaService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kampanya")
@RequiredArgsConstructor
public class KampanyaController {

    private final KampanyaService service;

    @PostMapping("/olustur")
    public KampanyaResponse olustur(@RequestBody KampanyaOlusturRequest r) {
        return service.olustur(r);
    }

    @GetMapping("/aktif")
    public List<KampanyaResponse> aktif() {
        return service.aktifKampanyalar();
    }

    @GetMapping("/kategori/{kategori}")
    public List<KampanyaResponse> kategori(@PathVariable String kategori) {
        return service.kategoriyeGore(kategori);
    }

    @GetMapping("/diyetisyen/{diyetisyenId}")
    public List<KampanyaResponse> dyKampanya(@PathVariable Long diyetisyenId) {
        return service.diyetisyenKampanyalari(diyetisyenId);
    }

    @GetMapping("/{id}")
    public KampanyaResponse getir(@PathVariable Long id) {
        return service.kampanyaGetir(id);
    }

    @PutMapping("/durum/{id}")
    public KampanyaResponse durumDegistir(
            @PathVariable Long id,
            @RequestParam boolean aktifMi) {
        return service.durumDegistir(id, aktifMi);
    }
}

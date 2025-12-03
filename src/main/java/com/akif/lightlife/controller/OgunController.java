package com.akif.lightlife.controller;

import com.akif.lightlife.dto.request.OgunKayitRequest;
import com.akif.lightlife.dto.request.OgunTarifEkleRequest;
import com.akif.lightlife.dto.response.OgunResponse;
import com.akif.lightlife.service.OgunService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ogun")
@RequiredArgsConstructor
public class OgunController {

    private final OgunService service;

    @PostMapping("/ekle")
    public OgunResponse ekle(@RequestBody OgunKayitRequest r) {
        return service.ogunEkle(r);
    }

    @PostMapping("/tarif-ekle")
    public OgunResponse tarifEkle(@RequestBody OgunTarifEkleRequest r) {
        return service.oguneTarifEkle(r);
    }

    @GetMapping("/{id}")
    public OgunResponse getir(@PathVariable Long id) {
        return service.ogunGetir(id);
    }

    @GetMapping("/gunluk/{kullaniciId}")
    public List<OgunResponse> gunluk(@PathVariable Long kullaniciId) {
        return service.kullanicininGunlukOgunleri(kullaniciId);
    }
}

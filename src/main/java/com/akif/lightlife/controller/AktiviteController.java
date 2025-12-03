package com.akif.lightlife.controller;

import com.akif.lightlife.dto.request.AktiviteEkleRequest;
import com.akif.lightlife.dto.response.AktiviteResponse;
import com.akif.lightlife.service.AktiviteService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aktivite")
@RequiredArgsConstructor
public class AktiviteController {

    private final AktiviteService service;

    @PostMapping("/ekle")
    public AktiviteResponse ekle(@RequestBody AktiviteEkleRequest r) {
        return service.ekle(r);
    }

    @GetMapping("/gunluk/{kullaniciId}")
    public List<AktiviteResponse> gunluk(@PathVariable Long kullaniciId) {
        return service.kullaniciGunlukAktivite(kullaniciId);
    }

    @GetMapping("/tum/{kullaniciId}")
    public List<AktiviteResponse> tum(@PathVariable Long kullaniciId) {
        return service.kullaniciTumAktiviteler(kullaniciId);
    }
}

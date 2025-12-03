package com.akif.lightlife.controller;

import com.akif.lightlife.dto.request.KiloKayitRequest;
import com.akif.lightlife.dto.response.KiloResponse;
import com.akif.lightlife.service.KiloService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kilo")
@RequiredArgsConstructor
public class KiloController {

    private final KiloService service;

    @PostMapping("/ekle")
    public KiloResponse ekle(@RequestBody KiloKayitRequest r) {
        return service.ekle(r);
    }

    @GetMapping("/kullanici/{id}")
    public List<KiloResponse> kullaniciKilolari(@PathVariable Long id) {
        return service.kullaniciKilolari(id);
    }

    @GetMapping("/bugun/{id}")
    public KiloResponse bugun(@PathVariable Long id) {
        return service.bugunKilo(id);
    }
}

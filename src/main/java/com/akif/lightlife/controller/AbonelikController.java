package com.akif.lightlife.controller;

import com.akif.lightlife.dto.request.AbonelikOlusturRequest;
import com.akif.lightlife.dto.response.AbonelikResponse;
import com.akif.lightlife.service.AbonelikService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/abonelik")
@RequiredArgsConstructor
public class AbonelikController {

    private final AbonelikService service;

    @PostMapping("/olustur")
    public AbonelikResponse olustur(@RequestBody AbonelikOlusturRequest r) {
        return service.abonelikOlustur(r);
    }

    @GetMapping("/kullanici/{kullaniciId}")
    public List<AbonelikResponse> kullaniciAbonelikleri(@PathVariable Long kullaniciId) {
        return service.kullaniciAbonelikleri(kullaniciId);
    }

    @PutMapping("/iptal/{abonelikId}")
    public AbonelikResponse iptal(@PathVariable Long abonelikId) {
        return service.abonelikIptal(abonelikId);
    }
}

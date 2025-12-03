package com.akif.lightlife.controller;

import com.akif.lightlife.dto.request.DestekTalebiOlusturRequest;
import com.akif.lightlife.dto.response.DestekTalebiResponse;
import com.akif.lightlife.service.DestekTalebiService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destek")
@RequiredArgsConstructor
public class DestekTalebiController {

    private final DestekTalebiService service;

    @PostMapping("/olustur")
    public DestekTalebiResponse olustur(@RequestBody DestekTalebiOlusturRequest r) {
        return service.talepOlustur(r);
    }

    @GetMapping("/kullanici/{kId}")
    public List<DestekTalebiResponse> kullaniciTalepleri(@PathVariable Long kId) {
        return service.kullaniciTalepleri(kId);
    }

    @PutMapping("/durum/{talepId}")
    public DestekTalebiResponse durumDegistir(
            @PathVariable Long talepId,
            @RequestParam String durum) {
        return service.talepDurumDegistir(talepId, durum);
    }
}

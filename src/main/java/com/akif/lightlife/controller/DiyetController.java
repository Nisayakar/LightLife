package com.akif.lightlife.controller;

import com.akif.lightlife.dto.request.DiyetOlusturRequest;
import com.akif.lightlife.dto.response.DiyetResponse;
import com.akif.lightlife.service.DiyetService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diyet")
@RequiredArgsConstructor
public class DiyetController {

    private final DiyetService service;

    @PostMapping("/olustur")
    public DiyetResponse olustur(@RequestBody DiyetOlusturRequest r) {
        return service.olustur(r);
    }

    @GetMapping("/kullanici/{id}")
    public List<DiyetResponse> aktifDiyetler(@PathVariable Long id) {
        return service.kullaniciAktifDiyetleri(id);
    }

    @PutMapping("/bitir/{id}")
    public DiyetResponse bitir(@PathVariable Long id) {
        return service.diyetBitir(id);
    }
}

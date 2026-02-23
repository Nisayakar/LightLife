package com.akif.lightlife.controller;

import com.akif.lightlife.dto.response.GunlukRaporResponse;
import com.akif.lightlife.dto.response.HaftalikRaporResponse;
import com.akif.lightlife.service.RaporService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rapor")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Frontend'den gelen istekleri kabul etmek için
public class RaporController {

    private final RaporService raporService;

    @GetMapping("/gunluk/{id}")
    public GunlukRaporResponse gunluk(@PathVariable Long id) {
        return raporService.gunlukRapor(id);
    }

    @GetMapping("/haftalik/{id}")
    public HaftalikRaporResponse haftalik(@PathVariable Long id) {
        return raporService.haftalikRapor(id);
    }
}
package com.akif.lightlife.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.akif.lightlife.dto.request.MalzemeRequest;
import com.akif.lightlife.dto.response.MalzemeResponse;
import com.akif.lightlife.service.MalzemeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/malzeme")
@RequiredArgsConstructor
public class MalzemeController {

    private final MalzemeService malzemeService;

    @PostMapping("/ekle/{tarifId}")
    public MalzemeResponse ekle(@PathVariable Long tarifId,
                                @RequestBody MalzemeRequest request) {
        return malzemeService.ekle(tarifId, request);
    }

    @GetMapping("/tarif/{tarifId}")
    public List<MalzemeResponse> tarifMalzemeleri(@PathVariable Long tarifId) {
        return malzemeService.tarifMalzemeleri(tarifId);
    }

    @DeleteMapping("/sil/{id}")
    public String sil(@PathVariable Long id) {
        malzemeService.sil(id);
        return "Malzeme silindi";
    }
}

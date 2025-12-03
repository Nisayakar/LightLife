package com.akif.lightlife.controller;

import com.akif.lightlife.dto.request.OgunTarifEkleRequest;
import com.akif.lightlife.dto.response.OgunTarifResponse;
import com.akif.lightlife.service.OgunDiyetTarifiService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ogun-tarif")
@RequiredArgsConstructor
public class OgunDiyetTarifiController {

    private final OgunDiyetTarifiService service;

    @PostMapping("/ekle")
    public OgunTarifResponse ekle(@RequestBody OgunTarifEkleRequest r) {
        return service.ekle(r);
    }

    @GetMapping("/{ogunId}")
    public List<OgunTarifResponse> liste(@PathVariable Long ogunId) {
        return service.ogunTarifleri(ogunId);
    }

    @DeleteMapping("/{id}")
    public void sil(@PathVariable Long id) {
        service.sil(id);
    }
}

package com.akif.lightlife.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akif.lightlife.dto.request.TarifOlusturRequest;
import com.akif.lightlife.dto.response.TarifResponse;
import com.akif.lightlife.service.DiyetTarifiService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tarif")
@RequiredArgsConstructor
public class DiyetTarifiController {

    private final DiyetTarifiService service;

    @PostMapping("/olustur")
    public TarifResponse olustur(@RequestBody TarifOlusturRequest r) {
        return service.tarifOlustur(r);
    }

    @GetMapping("/{id}")
    public TarifResponse getir(@PathVariable Long id) {
        return service.tarifGetir(id);
    }

    @GetMapping
    public List<TarifResponse> listele() {
        return service.tarifListele();
    }
}

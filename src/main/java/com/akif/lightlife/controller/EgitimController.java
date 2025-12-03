package com.akif.lightlife.controller;

import com.akif.lightlife.dto.request.EgitimOlusturRequest;
import com.akif.lightlife.dto.response.EgitimResponse;
import com.akif.lightlife.service.EgitimService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/egitim")
@RequiredArgsConstructor
public class EgitimController {

    private final EgitimService service;

    @PostMapping("/olustur")
    public EgitimResponse olustur(@RequestBody EgitimOlusturRequest r) {
        return service.olustur(r);
    }

    @GetMapping("/tum")
    public List<EgitimResponse> tum() {
        return service.tumEgitimler();
    }

    @GetMapping("/kategori/{kategori}")
    public List<EgitimResponse> kategori(@PathVariable String kategori) {
        return service.kategoriyeGore(kategori);
    }

    @GetMapping("/diyetisyen/{diyetisyenId}")
    public List<EgitimResponse> dyEgitimleri(@PathVariable Long diyetisyenId) {
        return service.diyetisyenEgitimleri(diyetisyenId);
    }

    @GetMapping("/{id}")
    public EgitimResponse getir(@PathVariable Long id) {
        return service.egitimGetir(id);
    }

    @PutMapping("/goruntule/{id}")
    public EgitimResponse goruntule(@PathVariable Long id) {
        return service.goruntule(id);
    }
}

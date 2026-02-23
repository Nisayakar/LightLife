package com.akif.lightlife.controller;

import com.akif.lightlife.dto.request.YiyecekKayitRequest;
import com.akif.lightlife.dto.request.YiyecekGuncelleRequest;
import com.akif.lightlife.dto.response.YiyecekResponse;
import com.akif.lightlife.service.YiyecekService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/yiyecek")
@RequiredArgsConstructor
public class YiyecekController {

    private final YiyecekService service;

    @PostMapping("/ekle")
    public YiyecekResponse ekle(@RequestBody YiyecekKayitRequest r) {
        return service.ekle(r);
    }

    @PutMapping("/guncelle/{id}")
    public YiyecekResponse guncelle(@PathVariable Long id, @RequestBody YiyecekGuncelleRequest r) {
        return service.guncelle(id, r);
    }

    @DeleteMapping("/sil/{id}")
    public void sil(@PathVariable Long id) {
        service.sil(id);
    }

    @GetMapping("/{id}")
    public YiyecekResponse getir(@PathVariable Long id) {
        return service.getir(id);
    }
    
    @GetMapping
    public List<YiyecekResponse> listele() {
        return service.listele();
    }
}

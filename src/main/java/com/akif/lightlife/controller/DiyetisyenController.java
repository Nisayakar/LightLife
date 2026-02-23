package com.akif.lightlife.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.akif.lightlife.dto.request.DiyetisyenGirisRequest;
import com.akif.lightlife.dto.request.DiyetisyenKayitRequest;
import com.akif.lightlife.dto.request.DiyetisyenProfilGuncelleRequest;
import com.akif.lightlife.dto.request.SifreGuncelleRequest; // Bunu oluşturacağız
import com.akif.lightlife.dto.response.DiyetisyenResponse;
import com.akif.lightlife.dto.response.KullaniciResponse;
import com.akif.lightlife.service.DiyetisyenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/diyetisyen")
@RequiredArgsConstructor
public class DiyetisyenController {

    private final DiyetisyenService service;

    // ====================== AUTH ======================

    @PostMapping("/kayit")
    public ResponseEntity<DiyetisyenResponse> kayit(@RequestBody DiyetisyenKayitRequest r) {
        return ResponseEntity.ok(service.kayit(r));
    }

    @PostMapping("/giris")
    public ResponseEntity<DiyetisyenResponse> login(@RequestBody DiyetisyenGirisRequest r) {
        return ResponseEntity.ok(service.giris(r));
    }

    // ====================== CRUD ======================

    @GetMapping("/{id}")
    public ResponseEntity<DiyetisyenResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<DiyetisyenResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // ====================== PROFİL GÜNCELLE ======================

    @PutMapping("/{id}")
    public ResponseEntity<DiyetisyenResponse> profilGuncelle(
            @PathVariable Long id,
            @RequestBody DiyetisyenProfilGuncelleRequest request
    ) {
        return ResponseEntity.ok(service.profilGuncelle(id, request));
    }

    // ====================== DİYETİSYENİN DANIŞANLARI ======================

    @GetMapping("/{id}/kullanicilar")
    public ResponseEntity<List<KullaniciResponse>> getKullanicilar(@PathVariable Long id) {
        return ResponseEntity.ok(service.getKullanicilar(id));
    }

    // ====================== GÜVENLİK (YENİ EKLENDİ) ======================

    @PutMapping("/{id}/sifre")
    public ResponseEntity<String> sifreDegistir(
            @PathVariable Long id,
            @RequestBody SifreGuncelleRequest request
    ) {
        service.sifreDegistir(id, request.getEskiSifre(), request.getYeniSifre());
        return ResponseEntity.ok("Şifre başarıyla güncellendi birtanem.");
    }
}
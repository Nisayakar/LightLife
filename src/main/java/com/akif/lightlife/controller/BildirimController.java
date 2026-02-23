package com.akif.lightlife.controller;

import com.akif.lightlife.dto.request.BildirimOlusturRequest;
import com.akif.lightlife.dto.response.BildirimResponse;
import com.akif.lightlife.service.BildirimService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bildirim")
@RequiredArgsConstructor
public class BildirimController {

    private final BildirimService service;

    @PostMapping("/gonder")
    public BildirimResponse gonder(@RequestBody BildirimOlusturRequest r) {
        return service.gonder(r);
    }

    @GetMapping("/kullanici/{kId}")
    public List<BildirimResponse> kullaniciBildirimleri(@PathVariable Long kId) {
        return service.kullaniciBildirimleri(kId);
    }

    @GetMapping("/okunmamis/{kId}")
    public List<BildirimResponse> okunmamis(@PathVariable Long kId) {
        return service.okunmamisBildirimler(kId);
    }

    @PutMapping("/okundu/{id}")
    public BildirimResponse okundu(@PathVariable Long id) {
        return service.okunduYap(id);
    }
    
    @PutMapping("/kullanici/{kullaniciId}/okundu")
    public ResponseEntity<Void> tumunuOkunduYap(@PathVariable Long kullaniciId) {
        service.tumunuOkunduYap(kullaniciId);
        return ResponseEntity.ok().build();
    }


}

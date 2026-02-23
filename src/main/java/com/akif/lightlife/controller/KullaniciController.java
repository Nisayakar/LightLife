package com.akif.lightlife.controller;

import com.akif.lightlife.dto.request.KullaniciGirisRequest;
import com.akif.lightlife.dto.request.KullaniciKayitRequest;
import com.akif.lightlife.dto.request.KullaniciProfilGuncelleRequest;
import com.akif.lightlife.dto.response.DiyetisyenResponse;
import com.akif.lightlife.dto.response.KullaniciResponse;
import com.akif.lightlife.exception.BadRequestException;
import com.akif.lightlife.service.KullaniciService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/kullanicilar")
@RequiredArgsConstructor
public class KullaniciController {

    private final KullaniciService kullaniciService;

    @PostMapping("/kayit")
    public ResponseEntity<KullaniciResponse> kayit(@Valid @RequestBody KullaniciKayitRequest request) {
        return ResponseEntity.ok(kullaniciService.kayit(request));
    }

    @PostMapping("/giris")
    public ResponseEntity<KullaniciResponse> giris(@Valid @RequestBody KullaniciGirisRequest request) {
        return ResponseEntity.ok(kullaniciService.giris(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<KullaniciResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(kullaniciService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KullaniciResponse> profilGuncelle(
            @PathVariable Long id, 
            @Valid @RequestBody KullaniciProfilGuncelleRequest request) {
        return ResponseEntity.ok(kullaniciService.profilGuncelle(id, request));
    }

    @PutMapping("/{id}/sifre")
    public ResponseEntity<Void> sifreGuncelle(
            @PathVariable Long id, 
            @RequestBody SifreGuncelleRequest req) {
        if (req.eskiSifre() == null || req.yeniSifre() == null) {
            throw new BadRequestException("Eski ve yeni şifre zorunludur");
        }
        kullaniciService.sifreGuncelle(id, req.eskiSifre(), req.yeniSifre());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/diyetisyen-ata")
    public ResponseEntity<Void> diyetisyenAta(
            @PathVariable Long id, 
            @RequestBody DiyetisyenAtaRequest req) {
        if (req.diyetisyenId() == null) {
            throw new BadRequestException("diyetisyenId zorunludur");
        }
        kullaniciService.diyetisyenAta(id, req.diyetisyenId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/diyetisyenler")
    public ResponseEntity<List<DiyetisyenResponse>> getKullanicininDiyetisyeni(@PathVariable Long id) {
        KullaniciResponse k = kullaniciService.getById(id);
        if (k.getDiyetisyen() == null && k.getDiyetisyenId() == null) {
            return ResponseEntity.ok(List.of());
        }
        DiyetisyenResponse d = k.getDiyetisyen() != null ? k.getDiyetisyen() :
                DiyetisyenResponse.builder()
                    .id(k.getDiyetisyenId())
                    .ad(k.getDiyetisyenAd())
                    .soyad(k.getDiyetisyenSoyad())
                    .build();
        return ResponseEntity.ok(List.of(d));
    }

    public record SifreGuncelleRequest(String eskiSifre, String yeniSifre) {}
    public record DiyetisyenAtaRequest(Long diyetisyenId) {}
}
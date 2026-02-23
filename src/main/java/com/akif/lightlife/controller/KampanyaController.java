package com.akif.lightlife.controller;

import com.akif.lightlife.dto.request.KampanyaOlusturRequest;
import com.akif.lightlife.dto.response.KampanyaResponse;
import com.akif.lightlife.entity.Kampanya;
import com.akif.lightlife.pattern.strategy.kampanya.KampanyaStratejiSecici;
import com.akif.lightlife.pattern.strategy.kampanya.KampanyaStratejisi;
import com.akif.lightlife.service.KampanyaService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kampanya")
@RequiredArgsConstructor
public class KampanyaController {

    private final KampanyaService service;

    @PostMapping("/olustur")
    public KampanyaResponse olustur(@RequestBody KampanyaOlusturRequest r) {
        return service.olustur(r);
    }

    @GetMapping("/aktif")
    public List<KampanyaResponse> aktif() {
        return service.aktifKampanyalar();
    }

    @GetMapping("/kategori/{kategori}")
    public List<KampanyaResponse> kategori(@PathVariable String kategori) {
        return service.kategoriyeGore(kategori);
    }

    @GetMapping("/diyetisyen/{diyetisyenId}")
    public List<KampanyaResponse> dyKampanya(@PathVariable Long diyetisyenId) {
        return service.diyetisyenKampanyalari(diyetisyenId);
    }

    @GetMapping("/{id}")
    public KampanyaResponse getir(@PathVariable Long id) {
        return service.kampanyaGetir(id);
    }

    @PutMapping("/durum/{id}")
    public KampanyaResponse durumDegistir(
            @PathVariable Long id,
            @RequestParam boolean aktifMi) {
        return service.durumDegistir(id, aktifMi);
    }

    @GetMapping("/{id}/fiyat")
    public double indirimliFiyat(
            @PathVariable Long id,
            @RequestParam double tutar
    ) {
        return service.indirimliTutarHesapla(id, tutar);
    }
    
 // KampanyaController.java içine eklenecek metodun DÜZELTİLMİŞ HALİ
    @GetMapping("/test-strateji")
    public ResponseEntity<String> testStrateji(@RequestParam String tip, @RequestParam Double tutar) {
        // Geçici bir kampanya nesnesi oluştur
        Kampanya k = new Kampanya();
        k.setKategori(tip); // YUZDE, SABIT, PREMIUM
        k.setIndirimYuzdesi(10); // Örnek değer: %10 veya 100 TL
        
        // Strategy Seçiciyi Çağır
        KampanyaStratejisi strateji = KampanyaStratejiSecici.stratejiSec(k);
        
        // HESAPLA (Düzeltilen Satır Burası)
        // Interface'deki isim 'indirimUygula' olduğu için onu kullanıyoruz.
        double sonuc = strateji.indirimUygula(tutar);
        
        return ResponseEntity.ok("Seçilen Strateji: " + strateji.getClass().getSimpleName() + 
                                 " | Eski Tutar: " + tutar + 
                                 " | Yeni Tutar: " + sonuc);
    }
}

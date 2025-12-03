package com.akif.lightlife.controller;


import com.akif.lightlife.dto.request.KullaniciKayitRequest;
import com.akif.lightlife.dto.request.KullaniciGirisRequest;
import com.akif.lightlife.dto.response.KullaniciResponse;
import com.akif.lightlife.service.KullaniciService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/kullanicilar")
public class KullaniciController {

    private final KullaniciService kullaniciService = null;

    @PostMapping("/kayit")
    public KullaniciResponse kayit(@RequestBody KullaniciKayitRequest request) {
        return kullaniciService.kayit(request);
    }

    @PostMapping("/giris")
    public KullaniciResponse giris(@RequestBody KullaniciGirisRequest request) {
        return kullaniciService.giris(request);
    }

    @GetMapping("/{id}")
    public KullaniciResponse getById(@PathVariable Long id) {
        return kullaniciService.getById(id);
    }

    @GetMapping
    public List<KullaniciResponse> getAll() {
        return kullaniciService.getAll();
    }
}

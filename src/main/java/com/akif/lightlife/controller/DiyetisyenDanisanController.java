package com.akif.lightlife.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.akif.lightlife.dto.response.DanisanOzetResponse;
import com.akif.lightlife.service.DiyetisyenDanisanService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/diyetisyen")
@RequiredArgsConstructor
@CrossOrigin // gerekiyorsa
public class DiyetisyenDanisanController {

    private final DiyetisyenDanisanService service;

    // Diyetisyenin danışanlarını getir
    @GetMapping("/{diyetisyenId}/danisanlar")
    public List<DanisanOzetResponse> danisanlar(
            @PathVariable Long diyetisyenId
    ) {
        return service.diyetisyeninDanisanlari(diyetisyenId);
    }

    // Diyetisyen, belirli bir kullanıcıyı kendine danışan olarak eklesin (şimdilik manuel)
    @PostMapping("/{diyetisyenId}/danisan-ekle/{kullaniciId}")
    public void danisanEkle(
            @PathVariable Long diyetisyenId,
            @PathVariable Long kullaniciId
    ) {
        service.danisanEkle(diyetisyenId, kullaniciId);
    }
}

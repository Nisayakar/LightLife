package com.akif.lightlife.controller;

import com.akif.lightlife.dto.request.HedefKaloriRequest;
import com.akif.lightlife.service.HedefService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hedef")
@RequiredArgsConstructor
public class HedefController {

    private final HedefService hedefService;

    // POST /api/hedef/kalori
    @PostMapping("/kalori")
    public int hedefKalori(@RequestBody HedefKaloriRequest request) {
        return hedefService.gunlukKaloriHesapla(request);
    }
}

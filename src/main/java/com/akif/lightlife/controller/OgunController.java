package com.akif.lightlife.controller;

import com.akif.lightlife.dto.request.OgunEkleRequest;
import com.akif.lightlife.dto.response.OgunResponse;
import com.akif.lightlife.service.OgunService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ogun")
@RequiredArgsConstructor
public class OgunController {

    private final OgunService ogunService;

    @PostMapping("/ekle")
    public OgunResponse ogunEkle(@RequestBody OgunEkleRequest request) {
        return ogunService.ogunEkle(request);
    }

    @GetMapping("/bugun/{kullaniciId}")
    public List<OgunResponse> bugunOgunleri(@PathVariable Long kullaniciId) {
        return ogunService.bugunOgunleri(kullaniciId);
    }
}

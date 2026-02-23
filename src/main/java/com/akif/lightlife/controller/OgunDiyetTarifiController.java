package com.akif.lightlife.controller;

import com.akif.lightlife.dto.request.OgunDiyetTarifiEkleRequest;
import com.akif.lightlife.dto.response.OgunDiyetTarifiResponse;
import com.akif.lightlife.service.OgunDiyetTarifiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ogun-tarifleri")
@RequiredArgsConstructor
public class OgunDiyetTarifiController {

    private final OgunDiyetTarifiService ogunDiyetTarifiService;

    // POST /api/ogun-tarifleri
    @PostMapping
    public OgunDiyetTarifiResponse tarifEkle(@RequestBody OgunDiyetTarifiEkleRequest request) {
        return ogunDiyetTarifiService.tarifEkle(request);
    }

    // GET /api/ogun-tarifleri/ogun/{ogunId}
    @GetMapping("/ogun/{ogunId}")
    public List<OgunDiyetTarifiResponse> ogunTarifleri(@PathVariable Long ogunId) {
        return ogunDiyetTarifiService.ogunTarifleri(ogunId);
    }
}

package com.akif.lightlife.controller;

import com.akif.lightlife.dto.request.EgzersizKayitRequest;
import com.akif.lightlife.dto.response.EgzersizResponse;
import com.akif.lightlife.service.EgzersizService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/egzersiz")
@RequiredArgsConstructor
public class EgzersizController {

    private final EgzersizService service;

    @PostMapping("/ekle")
    public EgzersizResponse ekle(@RequestBody EgzersizKayitRequest r) {
        return service.ekle(r);
    }

    @GetMapping("/kullanici/{id}")
    public List<EgzersizResponse> kullaniciEgzersizleri(@PathVariable Long id) {
        return service.kullaniciEgzersizleri(id);
    }

    @GetMapping("/bugun/{id}")
    public List<EgzersizResponse> bugununEgzersizleri(@PathVariable Long id) {
        return service.bugununEgzersizleri(id);
    }
}

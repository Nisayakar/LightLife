package com.akif.lightlife.controller;

import com.akif.lightlife.dto.request.MesajGonderRequest;
import com.akif.lightlife.dto.response.MesajResponse;
import com.akif.lightlife.service.MesajService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mesaj")
@RequiredArgsConstructor
public class MesajController {

    private final MesajService service;

    @PostMapping("/gonder")
    public MesajResponse mesajGonder(@RequestBody MesajGonderRequest r) {
        return service.mesajGonder(r);
    }

    @GetMapping("/sohbet/{kullaniciId}/{diyetisyenId}")
    public List<MesajResponse> sohbet(
            @PathVariable Long kullaniciId,
            @PathVariable Long diyetisyenId) {
        return service.sohbetGetir(kullaniciId, diyetisyenId);
    }
}

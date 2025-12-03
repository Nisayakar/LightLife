package com.akif.lightlife.controller;

import com.akif.lightlife.dto.request.OdemeYapRequest;
import com.akif.lightlife.dto.response.OdemeResponse;
import com.akif.lightlife.service.OdemeService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/odeme")
@RequiredArgsConstructor
public class OdemeController {

    private final OdemeService service;

    @PostMapping("/yap")
    public OdemeResponse odemeYap(@RequestBody OdemeYapRequest r) {
        return service.odemeYap(r);
    }

    @GetMapping("/abonelik/{abonelikId}")
    public List<OdemeResponse> abonelikOdemeleri(@PathVariable Long abonelikId) {
        return service.abonelikOdemeleri(abonelikId);
    }
}

package com.akif.lightlife.controller;

import com.akif.lightlife.dto.response.KullaniciPanelResponse;
import com.akif.lightlife.pattern.facade.KullaniciPanelFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/panel")
@RequiredArgsConstructor
public class KullaniciPanelController {

    private final KullaniciPanelFacade facade;

    @GetMapping("/{kullaniciId}")
    public KullaniciPanelResponse panelGetir(@PathVariable Long kullaniciId) {
        return facade.panelGetir(kullaniciId);
    }
}

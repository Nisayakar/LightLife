package com.akif.lightlife.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akif.lightlife.entity.KullaniciDiyetisyen;
import com.akif.lightlife.service.KullanıcıDiyetisyenService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1/kullanıcıdiyetisyen")
@RestController
@RequiredArgsConstructor

public class KullaniciDiyetisyenController{
	private final KullanıcıDiyetisyenService service;
	
	@PostMapping("/ata/{kullaniciId}/{diyetisyenId}")
	public KullaniciDiyetisyen ata(@PathVariable long kullaniciId , @PathVariable long diyetisyenId) {
		return service.ata(kullaniciId, diyetisyenId);
	}
	
	@GetMapping("/aktif/{kullaniciId}")
	public KullaniciDiyetisyen aktif(@PathVariable long kullaniciId) {
		return service.aktifDiyetisyen(kullaniciId);
	}
	
	@PostMapping("/degistir/{kullaniciId}/{diyetisyenId}")
	public void degistir(@PathVariable Long kullaniciId, @PathVariable Long diyetisyenId) {
		service.diyetisyenDegistir(kullaniciId, diyetisyenId);
	}
	
}

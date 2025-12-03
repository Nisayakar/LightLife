package com.akif.lightlife.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akif.lightlife.dto.request.DiyetisyenGirisRequest;
import com.akif.lightlife.dto.request.DiyetisyenKayitRequest;
import com.akif.lightlife.dto.response.DiyetisyenResponse;
import com.akif.lightlife.service.DiyetisyenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/diyetisyen")
@RequiredArgsConstructor
public class DiyetisyenController {
	
	private final DiyetisyenService service;
	
	@PostMapping("/kayit")
	public DiyetisyenResponse kayit(@RequestBody DiyetisyenKayitRequest r) {
		return service.kayit(r);
	}


    @PostMapping("/giris")
    public DiyetisyenResponse login(@RequestBody DiyetisyenGirisRequest r) {
        return service.giris(r);
    }

    @GetMapping("/{id}")
    public DiyetisyenResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<DiyetisyenResponse> getAll() {
        return service.getAll();
    }
}

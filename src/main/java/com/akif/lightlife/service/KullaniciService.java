package com.akif.lightlife.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.akif.lightlife.dto.request.KullaniciGirisRequest;
import com.akif.lightlife.dto.request.KullaniciKayitRequest;
import com.akif.lightlife.dto.response.KullaniciResponse;

@Service
public interface KullaniciService {
	
	KullaniciResponse kayit(KullaniciKayitRequest request);
	
	KullaniciResponse giris(KullaniciGirisRequest request);
	
	KullaniciResponse getById(Long id);
	
	List<KullaniciResponse> getAll();

}
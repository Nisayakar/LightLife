package com.akif.lightlife.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.akif.lightlife.dto.request.DiyetisyenGirisRequest;
import com.akif.lightlife.dto.request.DiyetisyenKayitRequest;
import com.akif.lightlife.dto.response.DiyetisyenResponse;

@Service
public interface DiyetisyenService {
	
	DiyetisyenResponse kayit(DiyetisyenKayitRequest request);
	DiyetisyenResponse giris(DiyetisyenGirisRequest request);
	DiyetisyenResponse getById(long id);
	List<DiyetisyenResponse> getAll();
}

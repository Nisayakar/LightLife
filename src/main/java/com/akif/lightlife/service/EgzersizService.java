package com.akif.lightlife.service;

import java.util.List;

import com.akif.lightlife.dto.request.EgzersizKayitRequest;
import com.akif.lightlife.dto.response.EgzersizResponse;

public interface EgzersizService {
	EgzersizResponse ekle(EgzersizKayitRequest r);
	
	List<EgzersizResponse>kullaniciEgzersizleri(Long kullaniciId);
	List<EgzersizResponse>bugununEgzersizleri(Long kullaniciId);
}

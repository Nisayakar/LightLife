package com.akif.lightlife.mapper;

import org.springframework.stereotype.Component;

import com.akif.lightlife.dto.response.EgzersizResponse;
import com.akif.lightlife.entity.Egzersiz;

@Component
public class EgzersizMapper {
	public EgzersizResponse toResponse(Egzersiz e) {
		return EgzersizResponse.builder()
				.id(e.getId())
				.ad(e.getAd())
				.sureDakika(e.getSureDakika())
				.kalori(e.getKalori())
				.tarih(e.getTarih().toString())
				.kullaniciId(e.getKullanici().getId())
				.build();
	}
}

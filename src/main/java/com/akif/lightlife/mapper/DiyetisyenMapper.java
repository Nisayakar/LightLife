package com.akif.lightlife.mapper;

import org.springframework.stereotype.Component;
import com.akif.lightlife.dto.response.DiyetisyenResponse;
import com.akif.lightlife.entity.Diyetisyen;

@Component
public class DiyetisyenMapper {
	
	public DiyetisyenResponse toResponse(Diyetisyen d) {
		return DiyetisyenResponse.builder()
				.id(d.getId())
				.ad(d.getAd())
				.soyad(d.getSoyad())
				.email(d.getEmail())
				.telefon(d.getTelefon())
				.uzmanlik(d.getUzmanlik())
				.deneyimYili(d.getDeneyimYili())
				.build();
	}
}

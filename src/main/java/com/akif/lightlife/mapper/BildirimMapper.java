package com.akif.lightlife.mapper;

import com.akif.lightlife.dto.response.BildirimResponse;
import com.akif.lightlife.entity.Bildirim;
import org.springframework.stereotype.Component;

@Component
public class BildirimMapper {

	public BildirimResponse toResponse(Bildirim b) {
	    return BildirimResponse.builder()
	        .id(b.getId())
	        .baslik(b.getBaslik())
	        .mesaj(b.getMesaj())
	        .okunduMu(b.isOkunduMu())
	        .tip(b.getTip())
	        .kaynak(b.getKaynak()) // 🔥 KRİTİK
	        .tarih(b.getTarih())
	        .build();
	}

}

package com.akif.lightlife.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DiyetisyenResponse {
	
	private long id;
	
	private String ad;
	
	private String soyad;
	
	private String email;
	
	private String telefon;
	
	private String uzmanlik;
	
	private Integer deneyimYili;
}

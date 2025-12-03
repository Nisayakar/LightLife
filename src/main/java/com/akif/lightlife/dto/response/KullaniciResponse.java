package com.akif.lightlife.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KullaniciResponse {
	private long id;
	private String ad;
	private String soyad;
	private String email;
	private String telefon;
	private String rol;
}

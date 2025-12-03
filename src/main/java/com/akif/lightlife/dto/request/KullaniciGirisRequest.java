package com.akif.lightlife.dto.request;

import lombok.Data;

@Data
public class KullaniciGirisRequest {
	private String email;
	private String sifre;
}

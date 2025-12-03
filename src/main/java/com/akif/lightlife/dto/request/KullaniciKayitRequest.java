package com.akif.lightlife.dto.request;

import lombok.Data;

@Data
public class KullaniciKayitRequest {
	private String ad;
	private String soyad;
	private String email;
	private String sifre;
	private String telefon;
}
package com.akif.lightlife.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EgzersizResponse {
	
	private Long id;
	
	private String ad;
	
	private int sureDakika;
	
	private int kalori;
	
	private String tarih;
	
	private Long kullaniciId;
}

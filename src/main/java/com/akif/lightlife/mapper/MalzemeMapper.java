package com.akif.lightlife.mapper;

import org.springframework.stereotype.Component;

import com.akif.lightlife.dto.response.MalzemeResponse;
import com.akif.lightlife.entity.Malzeme;

@Component
public class MalzemeMapper {

	public MalzemeResponse toResponse(Malzeme m) {

	    int kalori = (int) ((m.getYiyecek().getKalori() / 100.0) * m.getGram());

	    return MalzemeResponse.builder()
	            .id(m.getId())
	            .yiyecekAd(m.getYiyecek().getAd())  
	            .gram(m.getGram())
	            .kalori(kalori)
	            .build();
	}


}

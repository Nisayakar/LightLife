package com.akif.lightlife.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TarifResponse {

    private Long id;

    private String ad;

    private String aciklama;

    private int toplamKalori;

    private Long diyetisyenId; 

    private List<MalzemeResponse> malzemeler;
}

package com.akif.lightlife.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class TarifOlusturRequest {

    private String ad;
    private String aciklama;
    private Long diyetisyenId;
    private Long kullaniciId;
    private List<MalzemeRequest> malzemeler;
}

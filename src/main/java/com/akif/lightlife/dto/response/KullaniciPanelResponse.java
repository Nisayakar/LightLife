package com.akif.lightlife.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KullaniciPanelResponse {

    private GunlukRaporResponse gunlukRapor;
    private HaftalikRaporResponse haftalikRapor;
    private List<KampanyaResponse> aktifKampanyalar;
    
    private boolean abonelikAktif; 
    private long okunmamisBildirimSayisi;
    private String panelMesaji;
}

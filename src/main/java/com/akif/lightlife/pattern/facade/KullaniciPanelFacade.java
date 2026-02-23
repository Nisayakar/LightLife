package com.akif.lightlife.pattern.facade;

import com.akif.lightlife.dto.response.GunlukRaporResponse;
import com.akif.lightlife.dto.response.HaftalikRaporResponse;
import com.akif.lightlife.dto.response.KampanyaResponse;
import com.akif.lightlife.dto.response.KullaniciPanelResponse;
import com.akif.lightlife.entity.Kullanici;
import com.akif.lightlife.exception.NotFoundException;
import com.akif.lightlife.repository.KullaniciRepository;
import com.akif.lightlife.service.KampanyaService;
import com.akif.lightlife.service.RaporService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KullaniciPanelFacade {

    private final RaporService raporService;
    private final KampanyaService kampanyaService;
    private final KullaniciRepository kullaniciRepository;

    public KullaniciPanelResponse panelGetir(Long kullaniciId) {

        Kullanici k = kullaniciRepository.findById(kullaniciId)
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));

        GunlukRaporResponse gunlukRapor = raporService.gunlukRapor(kullaniciId);
        HaftalikRaporResponse haftalikRapor = raporService.haftalikRapor(kullaniciId);

        List<KampanyaResponse> kampanyalar = List.of();
        if (k.getDiyetisyen() != null) {
            Long diyetisyenId = k.getDiyetisyen().getId();
            kampanyalar = kampanyaService.diyetisyenKampanyalari(diyetisyenId);
        }

        return KullaniciPanelResponse.builder()
                .gunlukRapor(gunlukRapor)
                .haftalikRapor(haftalikRapor)
                .aktifKampanyalar(kampanyalar)
                .build();
    }
}

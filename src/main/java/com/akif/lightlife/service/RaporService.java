package com.akif.lightlife.service;

import com.akif.lightlife.dto.response.GunlukRaporResponse;
import com.akif.lightlife.dto.response.HaftalikRaporResponse;

public interface RaporService {

    GunlukRaporResponse gunlukRapor(Long kullaniciId);

    HaftalikRaporResponse haftalikRapor(Long id);
}

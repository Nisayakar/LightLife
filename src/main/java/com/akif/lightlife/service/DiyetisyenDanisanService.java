package com.akif.lightlife.service;


import java.util.List;

import com.akif.lightlife.dto.response.DanisanOzetResponse;

public interface DiyetisyenDanisanService {

    void danisanEkle(Long diyetisyenId, Long kullaniciId);

    List<DanisanOzetResponse> diyetisyeninDanisanlari(Long diyetisyenId);

    List<DanisanOzetResponse> kullanicininDiyetisyenleri(Long kullaniciId);
}

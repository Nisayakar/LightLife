package com.akif.lightlife.service;

import java.util.List;

import com.akif.lightlife.dto.request.MalzemeRequest;
import com.akif.lightlife.dto.response.MalzemeResponse;

public interface MalzemeService {

    MalzemeResponse ekle(Long tarifId, MalzemeRequest request);

    List<MalzemeResponse> tarifMalzemeleri(Long tarifId);

    void sil(Long malzemeId);
}

package com.akif.lightlife.service;
import java.util.List;

import com.akif.lightlife.dto.request.TarifOlusturRequest;
import com.akif.lightlife.dto.response.TarifResponse;

public interface DiyetTarifiService {

    TarifResponse tarifOlustur(TarifOlusturRequest request);

    TarifResponse tarifGetir(Long id);

    List<TarifResponse> tarifListele();
}

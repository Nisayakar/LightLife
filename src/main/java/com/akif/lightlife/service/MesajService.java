package com.akif.lightlife.service;

import com.akif.lightlife.dto.request.MesajGonderRequest;
import com.akif.lightlife.dto.response.MesajResponse;

import java.util.List;

public interface MesajService {

    MesajResponse mesajGonder(MesajGonderRequest request);

    List<MesajResponse> sohbetGetir(Long kullaniciId, Long diyetisyenId);
}

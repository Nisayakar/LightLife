package com.akif.lightlife.service;

import com.akif.lightlife.dto.request.OdemeYapRequest;
import com.akif.lightlife.dto.response.OdemeResponse;

import java.util.List;

public interface OdemeService {

    OdemeResponse odemeYap(OdemeYapRequest request);

    List<OdemeResponse> abonelikOdemeleri(Long abonelikId);
}

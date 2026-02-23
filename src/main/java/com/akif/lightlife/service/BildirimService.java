package com.akif.lightlife.service;

import com.akif.lightlife.dto.request.BildirimOlusturRequest;
import com.akif.lightlife.dto.response.BildirimResponse;

import java.util.List;

public interface BildirimService {

    BildirimResponse gonder(BildirimOlusturRequest request);

    List<BildirimResponse> kullaniciBildirimleri(Long kullaniciId);

    List<BildirimResponse> okunmamisBildirimler(Long kullaniciId);

    BildirimResponse okunduYap(Long id);
    void tumunuOkunduYap(Long kullaniciId);
   
}

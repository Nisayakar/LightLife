package com.akif.lightlife.mapper;

import com.akif.lightlife.dto.response.OgunResponse;
import com.akif.lightlife.entity.Ogun;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OgunMapper {

    private final OgunDiyetTarifiMapper odtMapper;

    public OgunResponse toResponse(Ogun o) {
        return OgunResponse.builder()
                .id(o.getId())
                .tip(o.getTip())
                .tarih(o.getTarih())
                .kullaniciId(o.getKullanici().getId())
                .tarifler(
                        o.getTarifler() == null ? null :
                                o.getTarifler()
                                        .stream()
                                        .map(odtMapper::toResponse)
                                        .toList()
                )
                .build();
    }
}

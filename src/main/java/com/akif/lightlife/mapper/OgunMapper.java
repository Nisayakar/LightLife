package com.akif.lightlife.mapper;

import com.akif.lightlife.dto.response.OgunResponse;
import com.akif.lightlife.entity.Ogun;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OgunMapper {

    private final OgunDiyetTarifiMapper ogunDiyetTarifiMapper;

    public OgunResponse toResponse(Ogun o) {
        return OgunResponse.builder()
                .id(o.getId())
                .kullaniciId(o.getKullanici().getId())
                .tarih(o.getTarih())
                .tip(o.getTip())
                .tarifler(
                        o.getTarifler() == null
                                ? List.of()
                                : o.getTarifler()
                                   .stream()
                                   .map(ogunDiyetTarifiMapper::toResponse)
                                   .toList()
                )
                .build();
    }
}

package com.akif.lightlife.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiyetTarifiSearchResponse {
    private Long id;
    private String ad;
    private String aciklama;
    private Long diyetisyenId;
    private int toplamKalori;
}

package com.akif.lightlife.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DanisanOzetResponse {
    private Long id;
    private String ad;
    private String soyad;
    private String email;
    private String telefon;
}

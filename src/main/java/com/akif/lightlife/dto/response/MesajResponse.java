package com.akif.lightlife.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class MesajResponse {

    private Long id;
    private Long gonderenId;
    private Long aliciId;
    private String icerik;
    private LocalDateTime tarih;
    private boolean okunduMu;
}

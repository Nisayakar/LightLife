package com.akif.lightlife.dto.request;

import lombok.Data;

@Data
public class MesajGonderRequest {

    private Long gonderenId;
    private Long aliciId;
    private String icerik;
}

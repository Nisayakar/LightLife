package com.akif.lightlife.dto.request;

import lombok.Data;

@Data
public class SifreGuncelleRequest {
    private String eskiSifre;
    private String yeniSifre;
}
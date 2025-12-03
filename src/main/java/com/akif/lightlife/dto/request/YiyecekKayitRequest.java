package com.akif.lightlife.dto.request;

import lombok.Data;

@Data
public class YiyecekKayitRequest {

    private String ad;
    private int kalori;
    private double karbonhidrat;
    private double protein;
    private double yag;
}

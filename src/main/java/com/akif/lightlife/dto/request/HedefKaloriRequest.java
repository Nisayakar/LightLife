package com.akif.lightlife.dto.request;

import com.akif.lightlife.enums.AktiviteDuzeyi;
import com.akif.lightlife.enums.Cinsiyet;
import lombok.Data;

@Data
public class HedefKaloriRequest {

    private int yas;
    private double kilo;        
    private double boy;         
    private Cinsiyet cinsiyet;  
    private AktiviteDuzeyi aktiviteDuzeyi;


    private double haftalikKiloDegisim;
}

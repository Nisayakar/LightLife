package com.akif.lightlife.pattern.facade;

import com.akif.lightlife.pattern.template.GunlukRapor;
import com.akif.lightlife.pattern.template.HaftalıkRapor;

public class GunlukRaporFacade {

    public void gunlukRaporOlustur() {
        new GunlukRapor().raporOlustur();
    }

    public void haftalikRaporOlustur() {
        new HaftalıkRapor().raporOlustur();
    }
}

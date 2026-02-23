package com.akif.lightlife.service.impl;

import com.akif.lightlife.dto.request.HedefKaloriRequest;
import com.akif.lightlife.pattern.strategy.hedef.HedefHesaplamaStrateji;
import com.akif.lightlife.pattern.strategy.hedef.KiloAlmaStratejisi;
import com.akif.lightlife.pattern.strategy.hedef.KiloKorumaStratejisi;
import com.akif.lightlife.pattern.strategy.hedef.KiloVermeStratejisi;
import com.akif.lightlife.service.HedefService;
import com.akif.lightlife.util.HesaplamaUtil;
import org.springframework.stereotype.Service;

@Service
public class HedefServiceImpl implements HedefService {

    @Override
    public int gunlukKaloriHesapla(HedefKaloriRequest r) {

      
        double bazKalori = HesaplamaUtil.bazKalori(
                r.getCinsiyet(),
                r.getKilo(),
                r.getBoy(),      
                r.getYas(),
                r.getAktiviteDuzeyi()
        );

        HedefHesaplamaStrateji strateji = secStrateji(r.getHaftalikKiloDegisim());

        return strateji.gunlukKalori(bazKalori, r.getHaftalikKiloDegisim());
    }

    private HedefHesaplamaStrateji secStrateji(double haftalikKiloDegisim) {
        if (haftalikKiloDegisim < 0) {
            return new KiloVermeStratejisi();
        } else if (haftalikKiloDegisim > 0) {
            return new KiloAlmaStratejisi();
        } else {
            return new KiloKorumaStratejisi();
        }
    }
}

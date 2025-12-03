package com.akif.lightlife.service.impl;

import com.akif.lightlife.dto.response.GunlukRaporResponse;
import com.akif.lightlife.dto.response.HaftalikRaporResponse;
import com.akif.lightlife.entity.DiyetTarifi;
import com.akif.lightlife.entity.Egzersiz;
import com.akif.lightlife.entity.Kilo;
import com.akif.lightlife.entity.Malzeme;
import com.akif.lightlife.entity.Ogun;
import com.akif.lightlife.entity.OgunDiyetTarifi;
import com.akif.lightlife.repository.EgzersizRepository;
import com.akif.lightlife.repository.KiloRepository;
import com.akif.lightlife.repository.OgunDiyetTarifiRepository;
import com.akif.lightlife.repository.OgunRepository;
import com.akif.lightlife.service.RaporService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RaporServiceImpl implements RaporService {

    private final OgunRepository ogunRepo;
    private final OgunDiyetTarifiRepository odtRepo;
    private final EgzersizRepository egzersizRepo;
    private final KiloRepository kiloRepo;

    @Override
    public GunlukRaporResponse gunlukRapor(Long kullaniciId) {
        LocalDate today = LocalDate.now();

        List<Ogun> ogunler = new ArrayList<>();
        for (Ogun o : ogunRepo.findAll()) {
            if (o.getKullanici() != null
                    && o.getKullanici().getId().equals(kullaniciId)
                    && today.equals(o.getTarih())) {
                ogunler.add(o);
            }
        }

        int toplamKalori = 0;
        double carb = 0, protein = 0, yag = 0;
        List<String> ogunIsimleri = new ArrayList<>();

        for (Ogun o : ogunler) {
            ogunIsimleri.add(o.getTip());

            for (OgunDiyetTarifi odt : o.getTarifler()) {
                DiyetTarifi t = odt.getTarif();
                int porsiyonKalori = odt.getPorsiyon() * t.getToplamKalori();
                toplamKalori += porsiyonKalori;

                for (Malzeme m : t.getMalzemeler()) {
                    double oran = m.getGram() / 100.0;
                    carb    += oran * m.getYiyecek().getKarbonhidrat();
                    protein += oran * m.getYiyecek().getProtein();
                    yag     += oran * m.getYiyecek().getYag();
                }
            }
        }

        int egzersizDakika = 0;
        int yakilanKalori = 0;
        for (Egzersiz e : egzersizRepo.findAll()) {
            if (e.getKullanici() != null
                    && e.getKullanici().getId().equals(kullaniciId)
                    && today.equals(e.getTarih())) {

                egzersizDakika += e.getSureDakika();
                yakilanKalori  += e.getKalori();
            }
        }

        Double bugunKilo = null;
        for (Kilo k : kiloRepo.findAll()) {
            if (k.getKullanici() != null
                    && k.getKullanici().getId().equals(kullaniciId)
                    && today.equals(k.getTarih())) {
                bugunKilo = k.getKiloDeger();
                break;
            }
        }

        return GunlukRaporResponse.builder()
                .toplamKalori(toplamKalori)
                .yakilanKalori(yakilanKalori)
                .karbonhidrat(carb)
                .protein(protein)
                .yag(yag)
                .ogunSayisi(ogunler.size())
                .egzersizDakika(egzersizDakika)
                .bugunKilo(bugunKilo)
                .ogunler(ogunIsimleri)
                .build();
    }

    @Override
    public HaftalikRaporResponse haftalikRapor(Long kullaniciId) {
        LocalDate today = LocalDate.now();
        LocalDate weekStart = today.minusDays(6); 

        Map<String, Integer> gunlukKaloriler = new LinkedHashMap<>();
        Map<String, Integer> gunlukYakilan = new LinkedHashMap<>();
        Map<String, Integer> gunlukOgunSayilari = new LinkedHashMap<>();

        double totalCarb = 0, totalProtein = 0, totalYag = 0;
        int gunSayisi = 0;

        List<Ogun> tumOgunler = ogunRepo.findAll();
        List<Egzersiz> tumEgzersizler = egzersizRepo.findAll();
        List<Kilo> tumKilolar = kiloRepo.findAll();

        for (LocalDate d = weekStart; !d.isAfter(today); d = d.plusDays(1)) {

            int gunKalori = 0;
            int gunYakilanKalori = 0;
            int ogunSayisi = 0;

            for (Ogun o : tumOgunler) {
                if (o.getKullanici() == null) continue;
                if (!o.getKullanici().getId().equals(kullaniciId)) continue;
                if (!d.equals(o.getTarih())) continue;

                ogunSayisi++;

                for (OgunDiyetTarifi odt : o.getTarifler()) {
                    DiyetTarifi t = odt.getTarif();
                    gunKalori += odt.getPorsiyon() * t.getToplamKalori();

                    for (Malzeme m : t.getMalzemeler()) {
                        double oran = m.getGram() / 100.0;
                        totalCarb    += oran * m.getYiyecek().getKarbonhidrat();
                        totalProtein += oran * m.getYiyecek().getProtein();
                        totalYag     += oran * m.getYiyecek().getYag();
                    }
                }
            }

            for (Egzersiz e : tumEgzersizler) {
                if (e.getKullanici() == null) continue;
                if (!e.getKullanici().getId().equals(kullaniciId)) continue;
                if (!d.equals(e.getTarih())) continue;

                gunYakilanKalori += e.getKalori();
            }

            gunlukKaloriler.put(d.toString(), gunKalori);
            gunlukYakilan.put(d.toString(), gunYakilanKalori);
            gunlukOgunSayilari.put(d.toString(), ogunSayisi);

            gunSayisi++;
        }

        List<Kilo> kullaniciKilolari = new ArrayList<>();
        for (Kilo k : tumKilolar) {
            if (k.getKullanici() != null
                    && k.getKullanici().getId().equals(kullaniciId)) {
                kullaniciKilolari.add(k);
            }
        }

        kullaniciKilolari.sort(Comparator.comparing(Kilo::getTarih));

        Double ilk = null;
        Double son = null;
        Double degisim = null;

        if (!kullaniciKilolari.isEmpty()) {
            ilk = kullaniciKilolari.get(0).getKiloDeger();
            son = kullaniciKilolari.get(kullaniciKilolari.size() - 1).getKiloDeger();
            degisim = son - ilk;
        }

        double ortCarb = gunSayisi > 0 ? totalCarb / gunSayisi : 0;
        double ortProtein = gunSayisi > 0 ? totalProtein / gunSayisi : 0;
        double ortYag = gunSayisi > 0 ? totalYag / gunSayisi : 0;

        return HaftalikRaporResponse.builder()
                .gunlukKaloriler(gunlukKaloriler)
                .gunlukYakilanKaloriler(gunlukYakilan)
                .gunlukOgunSayilari(gunlukOgunSayilari)
                .ilkKilo(ilk)
                .sonKilo(son)
                .degisim(degisim)
                .ortalamaKarbonhidrat(ortCarb)
                .ortalamaProtein(ortProtein)
                .ortalamaYag(ortYag)
                .build();
    }
}

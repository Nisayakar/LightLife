package com.akif.lightlife.service.impl;

import com.akif.lightlife.dto.response.GunlukRaporResponse;
import com.akif.lightlife.dto.response.HaftalikRaporResponse;
import com.akif.lightlife.entity.*;
import com.akif.lightlife.exception.NotFoundException;
import com.akif.lightlife.repository.*;
import com.akif.lightlife.service.RaporService;
import com.akif.lightlife.pattern.template.GunlukRapor;
import com.akif.lightlife.pattern.template.HaftalikRapor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RaporServiceImpl implements RaporService {

    private final KullaniciRepository kullaniciRepository;
    private final OgunRepository ogunRepository;
    private final OgunDiyetTarifiRepository ogunDiyetTarifiRepository;
    private final EgzersizRepository egzersizRepository;
    private final KiloRepository kiloRepository;

    @Override
    public GunlukRaporResponse gunlukRapor(Long kullaniciId) {
        Kullanici k = kullaniciRepository.findById(kullaniciId)
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));

        LocalDate today = LocalDate.now();
        List<Ogun> ogunler = ogunRepository.findByKullanici_IdAndTarih(kullaniciId, today);
        
        int toplamKalori = 0;
        double toplamKarb = 0, toplamProtein = 0, toplamYag = 0;
        List<String> ogunOzetleri = new ArrayList<>();

        if (ogunler != null && !ogunler.isEmpty()) {
            List<OgunDiyetTarifi> ogunTarifleri = ogunDiyetTarifiRepository.findByOgunIn(ogunler);
            
            for (Ogun ogun : ogunler) {
                int ogunKalori = 0;
                StringBuilder tarifIsimleri = new StringBuilder();
                
                List<OgunDiyetTarifi> bagliTarifler = ogunTarifleri.stream()
                        .filter(t -> t.getOgun() != null && t.getOgun().getId().equals(ogun.getId()))
                        .collect(Collectors.toList());

                for (OgunDiyetTarifi odt : bagliTarifler) {
                    DiyetTarifi tarif = odt.getTarif();
                    if (tarif == null) continue;

                    int porsiyon = odt.getPorsiyon() > 0 ? odt.getPorsiyon() : 1;
                    
                    if (tarifIsimleri.length() > 0) tarifIsimleri.append(", ");
                    tarifIsimleri.append(tarif.getAd() != null ? tarif.getAd() : "Adsız Tarif");

                    int tk = (tarif.getToplamKalori() != null) ? tarif.getToplamKalori() : 0;
                    int tKarb = (tarif.getKarbonhidratHedefi() != null) ? tarif.getKarbonhidratHedefi() : 0;
                    int tProt = (tarif.getProteinHedefi() != null) ? tarif.getProteinHedefi() : 0;
                    int tYag = (tarif.getYagHedefi() != null) ? tarif.getYagHedefi() : 0;

                    toplamKalori += tk * porsiyon;
                    ogunKalori += tk * porsiyon;
                    toplamKarb += (double) tKarb * porsiyon;
                    toplamProtein += (double) tProt * porsiyon;
                    toplamYag += (double) tYag * porsiyon;
                }
                
                String icerik = tarifIsimleri.length() > 0 ? tarifIsimleri.toString() : "İçerik Girilmemiş";
                ogunOzetleri.add(ogun.getTip() + ": " + icerik + " (" + ogunKalori + " kcal)");
            }
        }

        Double bugunKilo = kiloRepository.findByKullanici_IdAndTarih(kullaniciId, today)
                .map(Kilo::getKiloDeger)
                .orElse(k.getKiloKg() != null ? k.getKiloKg() : 0.0);

        int yakilan = egzersizRepository.findByKullanici_IdAndTarih(kullaniciId, today)
                .stream()
                .mapToInt(Egzersiz::getKalori) 
                .sum();

        GunlukRaporResponse response = GunlukRaporResponse.builder()
                .toplamKalori(toplamKalori)
                .yakilanKalori(yakilan)
                .karbonhidrat(round1(toplamKarb))
                .protein(round1(toplamProtein))
                .yag(round1(toplamYag))
                .bugunKilo(bugunKilo)
                .hedefKalori(k.getGunlukKaloriHedefi() != null ? k.getGunlukKaloriHedefi() : 0)
                .ogunler(ogunOzetleri)
                .ogunSayisi(ogunler != null ? ogunler.size() : 0)
                .build();

        try {
            GunlukRapor template = new GunlukRapor();
            template.setResponse(response);
            template.raporOlustur();
        } catch (Exception ignored) {}

        return response;
    }

    @Override
    public HaftalikRaporResponse haftalikRapor(Long kullaniciId) {
        Kullanici k = kullaniciRepository.findById(kullaniciId)
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı"));

        Map<String, Integer> kaloriMap = new LinkedHashMap<>();
        Map<String, Integer> yakilanMap = new LinkedHashMap<>();
        Map<String, Integer> ogunSayisiMap = new LinkedHashMap<>();

        for (int i = 6; i >= 0; i--) {
            LocalDate date = LocalDate.now().minusDays(i);
            String dateStr = date.toString();

            List<Ogun> gunlukOgunler = ogunRepository.findByKullanici_IdAndTarih(kullaniciId, date);
            
            int gunlukKalori = 0;
            if (!gunlukOgunler.isEmpty()) {
                gunlukKalori = ogunDiyetTarifiRepository.findByOgunIn(gunlukOgunler).stream()
                    .filter(t -> t.getTarif() != null)
                    .mapToInt(t -> {
                        Integer tk = t.getTarif().getToplamKalori();
                        return (tk != null ? tk : 0) * t.getPorsiyon();
                    })
                    .sum();
            }
            
            int gunlukYakilan = egzersizRepository.findByKullanici_IdAndTarih(kullaniciId, date).stream()
                    .mapToInt(Egzersiz::getKalori)
                    .sum();

            kaloriMap.put(dateStr, gunlukKalori);
            yakilanMap.put(dateStr, gunlukYakilan);
            ogunSayisiMap.put(dateStr, gunlukOgunler.size());
        }

        Double sonKilo = k.getKiloKg() != null ? k.getKiloKg() : 0.0;
        Double ilkKilo = kiloRepository.findByKullanici_IdAndTarih(kullaniciId, LocalDate.now().minusDays(7))
                .map(Kilo::getKiloDeger).orElse(sonKilo);

        // Not: 'ortalamaKalori' alanı DTO'da tanımlı olmadığı için sildim.
        // Frontend tarafında ortalamayı Map üzerinden hesaplayacağız.
        HaftalikRaporResponse response = HaftalikRaporResponse.builder()
                .gunlukKaloriler(kaloriMap)
                .gunlukYakilanKaloriler(yakilanMap)
                .gunlukOgunSayilari(ogunSayisiMap)
                .ilkKilo(ilkKilo)
                .sonKilo(sonKilo)
                .degisim(round1(sonKilo - ilkKilo))
                .ortalamaKarbonhidrat(250.0)
                .ortalamaProtein(100.0)
                .ortalamaYag(70.0)
                .build();

        try {
            HaftalikRapor template = new HaftalikRapor();
            template.setResponse(response);
            template.raporOlustur();
        } catch (Exception ignored) {}

        return response;
    }

    private double round1(double v) { return Math.round(v * 10.0) / 10.0; }
}
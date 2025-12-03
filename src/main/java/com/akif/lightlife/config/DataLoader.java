package com.akif.lightlife.config;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.akif.lightlife.entity.*;
import com.akif.lightlife.repository.*;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final KullaniciRepository kullaniciRepository;
    private final DiyetisyenRepository diyetisyenRepository;
    private final YiyecekRepository yiyecekRepository;

    @Bean
    CommandLineRunner run() {
        return args -> {

            if (kullaniciRepository.count() == 0) {

                Kullanici k1 = Kullanici.builder()
                        .ad("Mehmet")
                        .soyad("Güneş")
                        .email("mehmet@gmail.com")
                        .sifre("1234")
                        .telefon("5551112233")
                        .build();

                kullaniciRepository.save(k1);

                Diyetisyen d1 = Diyetisyen.builder()
                        .ad("Ayşe")
                        .soyad("Kara")
                        .email("ayse@gmail.com")
                        .sifre("1234")
                        .uzmanlik("Zayıflama")
                        .deneyimYili(5)
                        .build();

                diyetisyenRepository.save(d1);

                Yiyecek y1 = Yiyecek.builder()
                        .ad("Elma")
                        .kalori(52)
                        .karbonhidrat(14)
                        .protein(0.3)
                        .yag(0.2)
                        .build();

                yiyecekRepository.save(y1);

                System.out.println("✅ ÖRNEK VERİLER VERİTABANINA EKLENDİ!");
            }
        };
    }
}

package com.akif.lightlife.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "gunluk_rapor")
public class GunlukRaporKaydi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "kullanici_id")
    private Kullanici kullanici;

    private LocalDate tarih;

    private int toplamKalori;
    private int yakilanKalori;

    private double karbonhidrat;
    private double protein;
    private double yag;

    private int ogunSayisi;

    private int egzersizDakika;

    private Double kilo;
}

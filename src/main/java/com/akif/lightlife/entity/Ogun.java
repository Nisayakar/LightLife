package com.akif.lightlife.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ogun") // tablo adın farklıysa ona göre değiştir
public class Ogun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Kahvaltı / Öğle / Akşam / Ara Öğün vs.
    @Column(nullable = false)
    private String tip;

    @Column(nullable = false)
    private LocalDate tarih;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kullanici_id", nullable = false)
    private Kullanici kullanici;

    // OgunMapper'ın beklediği alan: getTarifler()
    @OneToMany(mappedBy = "ogun", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OgunDiyetTarifi> tarifler;
}

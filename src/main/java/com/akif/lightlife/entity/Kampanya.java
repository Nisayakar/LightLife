package com.akif.lightlife.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "kampanya")
public class Kampanya {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long diyetisyenId;

    @Column(nullable = false, length = 200)
    private String baslik;

    @Column(nullable = false, length = 1000)
    private String aciklama;

    private int indirimYuzdesi; 

    private LocalDate baslangicTarihi;
    private LocalDate bitisTarihi;

    private boolean aktifMi;

    private String kategori; 
}

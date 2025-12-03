package com.akif.lightlife.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "abonelik")
public class Abonelik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "kullanici_id", nullable = false)
    private Kullanici kullanici;


    @ManyToOne
    @JoinColumn(name = "diyetisyen_id", nullable = false)
    private Diyetisyen diyetisyen;

    @Column(nullable = false, length = 100)
    private String planAdi; 

    private int sureAy; 

    @Column(nullable = false)
    private BigDecimal aylikUcret;

    private LocalDate baslangicTarihi;

    private LocalDate bitisTarihi;

    private boolean aktifMi;
}

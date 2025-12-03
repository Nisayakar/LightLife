package com.akif.lightlife.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "diyet")
public class Diyet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "kullanici_id", nullable = false)
    private Kullanici kullanici;


    @ManyToOne
    @JoinColumn(name = "diyetisyen_id", nullable = false)
    private Diyetisyen diyetisyen;

    private String diyetAdi;

    private int gunlukKaloriHedefi;

    private int karbonhidratHedefi; 
    private int proteinHedefi;      
    private int yagHedefi;         

    @Column(length = 2000)
    private String sabah;  

    @Column(length = 2000)
    private String ogle;

    @Column(length = 2000)
    private String aksam;

    private LocalDate baslangicTarihi;
    private LocalDate bitisTarihi;

    private boolean aktifMi;
}

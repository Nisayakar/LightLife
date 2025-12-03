package com.akif.lightlife.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "aktivite")
public class Aktivite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int adimSayisi;

    private double mesafeKm; 

    private int sureDakika; 

    private int yakilanKalori;

    private LocalDate tarih;

    @ManyToOne
    @JoinColumn(name = "kullanici_id")
    private Kullanici kullanici;
}

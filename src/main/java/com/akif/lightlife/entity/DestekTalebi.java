package com.akif.lightlife.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "destek_talebi")
public class DestekTalebi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long kullaniciId;  

    @Column(nullable = false, length = 100)
    private String konu;        

    @Column(nullable = false, length = 1000)
    private String mesaj;  

    private LocalDateTime tarih;

    @Enumerated(EnumType.STRING)
    private TalepDurumu durum;

    public enum TalepDurumu {
        ACIK,
        INCELEMEDE,
        KAPALI
    }
}

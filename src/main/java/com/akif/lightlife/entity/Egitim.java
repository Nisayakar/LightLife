package com.akif.lightlife.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "egitim")
public class Egitim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long diyetisyenId; 

    @Column(nullable = false, length = 200)
    private String baslik;

    @Column(nullable = false, length = 5000)
    private String icerik;

    @Column(nullable = true)
    private String kategori; 

    private LocalDateTime tarih;

    private int goruntulenmeSayisi;
}

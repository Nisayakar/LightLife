package com.akif.lightlife.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ogun")
public class Ogun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tip; 

    private LocalDate tarih;

    @ManyToOne
    @JoinColumn(name = "kullanici_id")
    private Kullanici kullanici;

    @OneToMany(mappedBy = "ogun", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OgunDiyetTarifi> tarifler;
}

package com.akif.lightlife.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "malzeme")
public class Malzeme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double gram;

    @ManyToOne
    @JoinColumn(name = "yiyecek_id")
    private Yiyecek yiyecek;

    @ManyToOne
    @JoinColumn(name = "tarif_id")
    private DiyetTarifi tarif;
}

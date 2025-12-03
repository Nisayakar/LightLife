package com.akif.lightlife.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ogun_diyet_tarifi")
public class OgunDiyetTarifi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int porsiyon;

    @ManyToOne
    @JoinColumn(name = "ogun_id")
    private Ogun ogun;

    @ManyToOne
    @JoinColumn(name = "tarif_id")
    private DiyetTarifi tarif;
}
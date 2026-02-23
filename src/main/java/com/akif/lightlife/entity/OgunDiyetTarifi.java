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

    // Kullanıcı 1 porsiyon, 2 porsiyon gibi ekler
    private int porsiyon;

    @ManyToOne
    @JoinColumn(name = "ogun_id")
    private Ogun ogun;

    @ManyToOne
    @JoinColumn(name = "tarif_id")
    private DiyetTarifi tarif;

    // 🔥 Bu alan artık DB'de tutulmuyor → otomatik hesaplanıyor
    @Transient
    public int getToplamKalori() {
        if (tarif == null) return 0;
        return tarif.getToplamKalori() * porsiyon;
    }
}

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

    private double gram; // örn: 150 gram tavuk

    @ManyToOne
    @JoinColumn(name = "yiyecek_id")
    private Yiyecek yiyecek;

    @ManyToOne
    @JoinColumn(name = "tarif_id")
    private DiyetTarifi tarif;

    /**
     * 🔥 Bu malzemenin toplam kalorisi (ör: 150g tavuk → ~225 kcal)
     * Formül:
     *   (yiyecek.kalori * gram) / 100
     */
    @Transient
    public int getKalori() {
        if (yiyecek == null || yiyecek.getKalori() <= 0) {
            return 0;
        }

        // yiyecek.getKalori() → 100g için kalori
        double hesap = (yiyecek.getKalori() * (gram / 100.0));

        return (int) Math.round(hesap);
    }
}

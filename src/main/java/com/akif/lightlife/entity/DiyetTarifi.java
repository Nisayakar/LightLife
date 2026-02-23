package com.akif.lightlife.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "diyet_tarifi")
public class DiyetTarifi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ad;
    private String aciklama;

    @Column(name = "toplam_kalori", nullable = false)
    private Integer toplamKalori;

    // Eksik olan makro alanları eklendi
    @Column(name = "karbonhidrat_hedefi")
    private Integer karbonhidratHedefi;

    @Column(name = "protein_hedefi")
    private Integer proteinHedefi;

    @Column(name = "yag_hedefi")
    private Integer yagHedefi;

    @OneToMany(mappedBy = "tarif", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Malzeme> malzemeler = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "diyetisyen_id", nullable = false)
    private Diyetisyen diyetisyen;

    public int hesaplananToplamKalori() {
        if (malzemeler == null || malzemeler.isEmpty()) return 0;
        return malzemeler.stream()
                .mapToInt(m -> {
                    if (m.getYiyecek() == null) return 0;
                    return (int) Math.round((m.getYiyecek().getKalori() * m.getGram()) / 100.0);
                }).sum();
    }
}
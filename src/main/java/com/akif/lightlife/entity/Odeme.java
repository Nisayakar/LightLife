package com.akif.lightlife.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "odeme")
public class Odeme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "abonelik_id", nullable = false)
    private Abonelik abonelik;

    @Column(nullable = false)
    private BigDecimal tutar;

    @Column(nullable = false, length = 20)
    private String odemeYontemi;

    @Enumerated(EnumType.STRING)
    private OdemeDurumu durum;

    private LocalDateTime tarih;

    @Column(length = 100)
    private String islemNo;

    public enum OdemeDurumu {
        BASARILI,
        BASARISIZ,
        BEKLIYOR
    }
}

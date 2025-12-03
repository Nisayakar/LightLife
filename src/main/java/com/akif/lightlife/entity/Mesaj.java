package com.akif.lightlife.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "mesaj")
public class Mesaj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long gonderenId;

    private Long aliciId;

    @Column(nullable = false, length = 500)
    private String icerik;

    private LocalDateTime tarih;

    private boolean okunduMu;
}

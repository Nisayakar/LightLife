package com.akif.lightlife.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bildirim")
public class Bildirim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long kullaniciId;

    private String baslik;

    @Column(length = 2000)
    private String mesaj;

    private boolean okunduMu;
    
    private String kaynak; // SISTEM, DANISAN, DIYETISYEN


    private String tip; // KAMPANYA, EGITIM, HEDEF, MESAJ, SISTEM

    private LocalDateTime tarih;
}

package com.akif.lightlife.entity;

import com.akif.lightlife.enums.Cinsiyet;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "kullanicilar")
public class Kullanici {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ad;
    private String soyad;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String sifre;

    private String telefon;
    private String rol;
    private LocalDateTime kayitTarihi;

    private Integer yas;           
    private Double boyCm;         
    private Double kiloKg;        
    private Double hedefKilo;     
    private Integer gunlukKaloriHedefi; // Hesaplanan TDEE hedefi buraya kaydolacak

    @Enumerated(EnumType.STRING)
    private Cinsiyet cinsiyet;   

    @Column(length = 1000)
    private String saglikNotu;    

    @ManyToOne
    @JoinColumn(name = "diyetisyen_id")
    private Diyetisyen diyetisyen;

    @PrePersist
    public void kayitZamani() {
        this.kayitTarihi = LocalDateTime.now();
        if (this.rol == null) {
            this.rol = "KULLANICI";
        }
    }
}
package com.akif.lightlife.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Data 
@NoArgsConstructor 
@AllArgsConstructor
@Entity 
@Table(name = "kullanicilar")
@Builder
public class Kullanici {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String ad;
    
    private String soyad;
    
    @Column(unique = true , nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String sifre;
    
    private String telefon;

    private String rol = "Kullanici";
    
    private LocalDateTime kayitTarihi;
    
    @PrePersist
    public void kayitZamani() {
    	this.kayitTarihi = LocalDateTime.now();
    }
}


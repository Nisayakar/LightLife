package com.akif.lightlife.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity 
@Table(name = "diyetisyen")
public class Diyetisyen {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String ad;
    
    private String soyad;
    
    @Column(unique = true , nullable = false)
    private String email;
    
    private String telefon;
    
    private String uzmanlik;
    
    private String sifre;
    
    private Integer deneyimYili;
}
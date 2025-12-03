package com.akif.lightlife.entity;

import jakarta.persistence.*;
import lombok.*;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity 
@Table(name = "kullanici_diyetisyen")
public class KullaniciDiyetisyen {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "kullanici_id" , nullable = false)
    private Kullanici kullanici;
    
    @ManyToOne
    @JoinColumn(name = "diyetisyen_id" , nullable = false)
    private Diyetisyen diyetisyen;
    
    private boolean aktif = true;
}
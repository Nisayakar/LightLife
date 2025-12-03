package com.akif.lightlife.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Data 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
@Entity 
@Table(name = "egzersiz")
public class Egzersiz {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String ad;
    
    private int sureDakika;
    
    private int kalori;
    
    private LocalDate tarih;
    
    @ManyToOne
    @JoinColumn(name = "kullanici_id" , nullable = false)
    private Kullanici kullanici;
}
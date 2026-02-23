package com.akif.lightlife.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "yiyecek")
public class Yiyecek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ad;

    private Double kalori;             
    private Double karbonhidrat;    
    private Double protein;         
    private Double yag;              
}

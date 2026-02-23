package com.akif.lightlife.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.node.BooleanNode;

@Entity
@Table(name = "kullanici_diyetisyen")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KullaniciDiyetisyen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "kullanici_id")
    private Kullanici kullanici;

    @ManyToOne(optional = false)
    @JoinColumn(name = "diyetisyen_id")
    private Diyetisyen diyetisyen;

    @Column(name = "baslama_tarihi", nullable = false)
    private LocalDate baslamaTarihi = LocalDate.now();

    @Column(name = "bitis_tarihi")
    private LocalDate bitisTarihi;
    
    private Boolean aktif;

	public void setAktif(boolean b) {
		
	}
}

package com.akif.lightlife.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "diyet_tarifi")
public class DiyetTarifi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ad;

    private String aciklama;

    private int toplamKalori; 

    @OneToMany(mappedBy = "tarif", cascade = CascadeType.ALL)
    private List<Malzeme> malzemeler;

    @ManyToOne
    @JoinColumn(name = "diyetisyen_id")
    private Diyetisyen diyetisyen;
}

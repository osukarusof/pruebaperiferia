package com.periferia.mutant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "adn")
public class MutantEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "secuencia_dna", nullable = false)
    private String secuenciaDna;

    @Column(name = "es_mutante", nullable = false)
    private Boolean esMutante;
}

package com.periferia.mutant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dna")
public class MutantEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /*@Column(name = "secuencia_dna", nullable = false)
    @ElementCollection
    private List<String> secuenciaDna;

    @Column(name = "es_mutante", nullable = false)
    private Boolean esMutante;*/
}

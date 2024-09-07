package com.periferia.mutant.entity;

import com.periferia.mutant.utils.DnaSequenceArrayConverter;
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
@Table(name = "dna")
public class MutantEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dna_sequence", nullable = false, unique = true)
    @Convert(converter = DnaSequenceArrayConverter.class)
    private String[] dna;

    @Column(name = "is_mutant", nullable = false)
    private Boolean isMutant;
}

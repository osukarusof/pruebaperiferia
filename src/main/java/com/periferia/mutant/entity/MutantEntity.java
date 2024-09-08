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

    @Column(name = "adn_sequence", nullable = false, columnDefinition = "TEXT")
    private String dnaSequence;

    @Column(name = "is_mutant", nullable = false)
    private Boolean isMutant;

}

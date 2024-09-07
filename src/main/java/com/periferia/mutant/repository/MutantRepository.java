package com.periferia.mutant.repository;

import com.periferia.mutant.entity.MutantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MutantRepository extends JpaRepository<MutantEntity, Long> {

    @Query(value = "SELECT * FROM dna WHERE JSON_CONTAINS(dna_sequence, :dnaSecuence, '$')", nativeQuery = true)
    Optional<MutantEntity> findByDnaSequence(@Param("dnaSecuence") String dnaSecuence);
}

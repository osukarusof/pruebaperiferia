package com.periferia.mutant.repository;

import com.periferia.mutant.dto.IsMutantCalculate;
import com.periferia.mutant.entity.MutantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MutantRepository extends JpaRepository<MutantEntity, Long> {

    @Query(value = "select m from MutantEntity m where m.dnaSequence = :dnaSequence")
    Optional<MutantEntity> findByDnaSequence(@Param("dnaSequence") String dnaSequence);

    @Query(value = "SELECT \n" +
            "    SUM(is_mutant) AS countMutantDna, \n" +
            "    COUNT(*) - SUM(is_mutant) AS countHumanDna,\n" +
            "    COALESCE(SUM(is_mutant) / NULLIF(COUNT(*) - SUM(is_mutant), 0), 0) AS ratio\n" +
            "FROM adn;\n", nativeQuery = true)
    Optional<IsMutantCalculate> getMutantCalculate();
}

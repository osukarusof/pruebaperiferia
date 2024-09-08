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

    @Query("select " +
            "sum(case when m.isMutant = true then 1 else 0 end) as countMutantDna, " +
            "sum(case when m.isMutant = false then 1 else 0 end) as countHumanDna, " +
            "case when sum(case when m.isMutant = false then 1 else 0 end) = 0 " +
            "then 0 else " +
            "sum(case when m.isMutant = true then 1 else 0 end) / " +
            "sum(case when m.isMutant = false then 1 else 0 end) " +
            "end as ratio " +
            "from MutantEntity m")
    Optional<IsMutantCalculate> getMutantCalculate();
}

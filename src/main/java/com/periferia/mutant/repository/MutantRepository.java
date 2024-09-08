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
            "coalesce(sum(case when m.isMutant = true then 1 else 0 end) / nullif(sum(case when m.isMutant = false then 1 else 0 end), 0), 0) as ratio " +
            "from MutantEntity m " +
            "group by m.isMutant " +
            "having sum(case when m.isMutant = true then 1 else 0 end) > 0 or sum(case when m.isMutant = false then 1 else 0 end) > 0")
    Optional<IsMutantCalculate> getMutantCalculate();
}

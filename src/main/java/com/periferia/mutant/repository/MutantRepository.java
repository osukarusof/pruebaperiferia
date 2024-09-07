package com.periferia.mutant.repository;

import com.periferia.mutant.entity.MutantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MutantRepository extends JpaRepository<MutantEntity, Long> {
}

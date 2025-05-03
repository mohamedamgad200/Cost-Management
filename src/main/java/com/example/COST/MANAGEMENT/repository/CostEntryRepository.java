package com.example.COST.MANAGEMENT.repository;

import com.example.COST.MANAGEMENT.model.CostEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostEntryRepository extends JpaRepository<CostEntry, Long> {
}

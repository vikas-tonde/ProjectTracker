package com.project.ProjectTracker.Dao;

import com.project.ProjectTracker.entity.Phase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PhaseRepository extends JpaRepository<Phase, Long> {
    Phase findByPhaseName(String phaseName);

    @Query(value = "select sum(percentage) from phase where phase_id<?1", nativeQuery = true)
    int sumPercentage(Long id);

    @Query(value = "select sum(percentage) from phase where phase_id<=?1", nativeQuery = true)
    int sumPercentageWithCurrent(Long id);
}

package com.project.ProjectTracker.service;

import com.project.ProjectTracker.Dao.PhaseRepository;
import com.project.ProjectTracker.entity.Phase;
import com.project.ProjectTracker.entity.Project;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class PhaseService {
    private PhaseRepository phaseRepository;
    public String getProgress(Phase phase)
    {
        int sumPercentage = phaseRepository.sumPercentage(phase.getPhaseId());
        return String.valueOf(sumPercentage);
    }

    public Date getExpectedCompletionDate(Project project)
    {
        Date deadline = project.getDeadline();
        Date added = project.getDateAdded();
        long days = ChronoUnit.DAYS.between(added.toLocalDate(), deadline.toLocalDate());
        int sumPercentage = phaseRepository.sumPercentageWithCurrent(project.getPhase().getPhaseId());
        double daysOfPhase = Math.floor((days * sumPercentage) / 100);
        Date expectedDate = new Date((long) (System.currentTimeMillis()+(daysOfPhase*1000*60*60*24)));
        return expectedDate;

    }
}

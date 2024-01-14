package ru.mronethree.labelscum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mronethree.labelscum.domain.Release;
import ru.mronethree.labelscum.domain.ReleaseExpense;

import java.util.List;

public interface ReleaseExpenseRepository extends JpaRepository<ReleaseExpense, Integer> {
    List<ReleaseExpense> findAllByRelease(Release release);
}
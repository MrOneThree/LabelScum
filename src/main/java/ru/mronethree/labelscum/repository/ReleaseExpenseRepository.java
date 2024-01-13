package ru.mronethree.labelscum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mronethree.labelscum.domain.ReleaseExpense;

public interface ReleaseExpenseRepository extends JpaRepository<ReleaseExpense, Integer> {
}
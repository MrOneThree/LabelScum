package ru.mronethree.labelscum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mronethree.labelscum.domain.CurrencyToDate;

public interface CurrencyToDateRepository extends JpaRepository<CurrencyToDate, Integer> {
}
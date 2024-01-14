package ru.mronethree.labelscum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mronethree.labelscum.domain.Curr;
import ru.mronethree.labelscum.domain.CurrencyToDate;

import java.time.LocalDate;
import java.util.List;

public interface CurrencyToDateRepository extends JpaRepository<CurrencyToDate, Integer> {
    List<CurrencyToDate> findCurrencyToDateByDate(LocalDate date);
    boolean existsByCurrencyAndDate(Curr currency, LocalDate date);
}
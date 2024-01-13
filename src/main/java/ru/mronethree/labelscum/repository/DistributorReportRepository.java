package ru.mronethree.labelscum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mronethree.labelscum.domain.DistributorReport;

public interface DistributorReportRepository extends JpaRepository<DistributorReport, Long> {
}
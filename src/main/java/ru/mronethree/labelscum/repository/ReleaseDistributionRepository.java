package ru.mronethree.labelscum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mronethree.labelscum.domain.ReleaseDistribution;

public interface ReleaseDistributionRepository extends JpaRepository<ReleaseDistribution, Integer> {
}
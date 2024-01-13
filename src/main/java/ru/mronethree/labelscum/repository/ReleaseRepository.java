package ru.mronethree.labelscum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mronethree.labelscum.domain.Release;

public interface ReleaseRepository extends JpaRepository<Release, Integer> {
}
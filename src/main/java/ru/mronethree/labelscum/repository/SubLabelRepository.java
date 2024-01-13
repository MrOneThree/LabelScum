package ru.mronethree.labelscum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mronethree.labelscum.domain.Label;

public interface SubLabelRepository extends JpaRepository<Label, Integer> {
}
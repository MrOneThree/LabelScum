package ru.mronethree.labelscum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mronethree.labelscum.domain.ReleaseArtists;

public interface ReleaseArtistsRepository extends JpaRepository<ReleaseArtists, Integer> {
}
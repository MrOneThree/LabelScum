package ru.mronethree.labelscum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mronethree.labelscum.domain.MusicDistributor;

import java.util.Optional;

public interface MusicDistributorRepository extends JpaRepository<MusicDistributor, Integer> {
    Optional<MusicDistributor> findMusicDistributorByName(String name);
}
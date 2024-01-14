package ru.mronethree.labelscum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mronethree.labelscum.domain.Artist;
import ru.mronethree.labelscum.domain.ReleaseArtists;

import java.util.List;

public interface ReleaseArtistsRepository extends JpaRepository<ReleaseArtists, Integer> {
    List<ReleaseArtists> findAllByArtist(Artist artist);
}
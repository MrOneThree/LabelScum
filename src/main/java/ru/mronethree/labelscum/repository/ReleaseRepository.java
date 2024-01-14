package ru.mronethree.labelscum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.mronethree.labelscum.domain.Artist;
import ru.mronethree.labelscum.domain.Release;

import java.util.List;

public interface ReleaseRepository extends JpaRepository<Release, Integer> {
    @Query("select r from Release r left join fetch r.releaseArtists ra left join fetch r.releaseExpenses re where ra.artist = ?1")
    List<Release> fetchByArtistWithExpenses(Artist artist);
}
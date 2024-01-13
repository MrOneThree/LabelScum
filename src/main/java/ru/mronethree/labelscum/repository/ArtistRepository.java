package ru.mronethree.labelscum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mronethree.labelscum.domain.Artist;

import java.util.Optional;

/**
 * @author Kirill Popov
 */
@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    Optional<Artist> findArtistByAlias(String alias);
}

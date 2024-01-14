package ru.mronethree.labelscum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.mronethree.labelscum.domain.Artist;
import ru.mronethree.labelscum.domain.DistributorReport;
import ru.mronethree.labelscum.domain.MusicDistributor;

import java.time.LocalDate;
import java.util.List;

public interface DistributorReportRepository extends JpaRepository<DistributorReport, Long> {
    boolean existsByMusicDistributorAndArtistAndDate(MusicDistributor distributor, Artist artist, LocalDate date);
    List<DistributorReport> findAllByArtist(Artist artist);
    @Query("select r from DistributorReport r where r.artist = ?1 and year(r.date) = ?2")
    List<DistributorReport> findAllByArtistAndYear(Artist artist, Integer year);
}
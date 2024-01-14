package ru.mronethree.labelscum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.mronethree.labelscum.domain.Artist;
import ru.mronethree.labelscum.domain.DistributorReport;
import ru.mronethree.labelscum.repository.DistributorReportRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kirill Popov
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DistributorReportService {
    @Autowired
    private final DistributorReportRepository repository;
    @Autowired
    private final CbrCurrencyToDateService cbrCurrencyToDateService;

    public List<DistributorReport> fetchAll(){
        log.info("Fetching all Distributor reports");
        return repository.findAll();
    }
    public List<DistributorReport> fetchByArtist(Artist artist){
        log.info("Fetching all Distributor reports for: {}", artist.getAlias());
        return repository.findAllByArtist(artist);
    }
    public List<DistributorReport> fetchByArtistAndYear(Artist artist, Integer year){
        log.info("Fetching all Distributor reports for: {} and year: {}", artist.getAlias(), year);
        return repository.findAllByArtistAndYear(artist, year);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<DistributorReport> saveBatch(List<DistributorReport> reports){
        log.info("Saving batch of reports");
//        TODO: optimize
        var filteredReport = reports.stream()
                            .filter(r -> !repository.existsByMusicDistributorAndArtistAndDate(
                                            r.getMusicDistributor(),
                                            r.getArtist(),
                                            r.getDate()))
                .collect(Collectors.toSet());
        filteredReport.stream().map(DistributorReport::getDate)
                .distinct()
                .forEach(cbrCurrencyToDateService::update);

        return repository.saveAll(filteredReport);
    }
}

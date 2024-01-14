package ru.mronethree.labelscum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mronethree.labelscum.domain.Artist;
import ru.mronethree.labelscum.domain.Release;
import ru.mronethree.labelscum.repository.ReleaseRepository;

import java.util.List;

/**
 * @author Kirill Popov
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ReleaseService {
    @Autowired
    private final ReleaseRepository repository;

    public List<Release> fetchAll(){
        log.info("Fetching all releases");
        return repository.findAll();
    }

    @Transactional
    public List<Release> fetchByArtistWithExpenses(Artist artist){
        log.info("Fetching releases for: {}", artist.getAlias());
        return repository.fetchByArtistWithExpenses(artist);
    }
}

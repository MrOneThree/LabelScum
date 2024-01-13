package ru.mronethree.labelscum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mronethree.labelscum.domain.Artist;
import ru.mronethree.labelscum.repository.ArtistRepository;

import java.util.List;

/**
 * @author Kirill Popov
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ArtistService {
    @Autowired
    private final ArtistRepository artistRepository;

    public List<Artist> fetchAll(){
        log.info("Fetching all artists");
        return artistRepository.findAll();
    }
    public Artist findByAlias(String alias){
        log.info("Fetching artists with alias: {}", alias);
        return artistRepository.findArtistByAlias(alias).orElseThrow();
    }
}

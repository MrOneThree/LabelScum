package ru.mronethree.labelscum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mronethree.labelscum.domain.MusicDistributor;
import ru.mronethree.labelscum.repository.MusicDistributorRepository;

import java.util.List;

/**
 * @author Kirill Popov
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class MusicDistributorService {
    @Autowired
    private final MusicDistributorRepository repository;

    public List<MusicDistributor> fetchAll(){
        log.info("Fetching all Music distributors");
        return repository.findAll();
    }
    public MusicDistributor findByName(String name){
        log.info("Fetching Music distributor with name: {}", name);
        return repository.findMusicDistributorByName(name).orElseThrow();
    }
}

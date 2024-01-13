package ru.mronethree.labelscum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}

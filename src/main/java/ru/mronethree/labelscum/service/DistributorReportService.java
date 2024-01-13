package ru.mronethree.labelscum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mronethree.labelscum.domain.DistributorReport;
import ru.mronethree.labelscum.repository.DistributorReportRepository;

import java.util.List;

/**
 * @author Kirill Popov
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DistributorReportService {
    @Autowired
    private final DistributorReportRepository repository;

    public List<DistributorReport> fetchAll(){
        log.info("Fetching all Distributor reports");
        return repository.findAll();
    }
    public DistributorReport save(DistributorReport report){
        log.info("Saving report from {} dated {}", report.getMusicDistributor(), report.getDate());
        return repository.save(report);
    }
}

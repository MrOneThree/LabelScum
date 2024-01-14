package ru.mronethree.labelscum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.mronethree.labelscum.domain.Release;
import ru.mronethree.labelscum.domain.ReleaseExpense;
import ru.mronethree.labelscum.repository.ReleaseExpenseRepository;

import java.util.List;

/**
 * @author Kirill Popov
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ReleaseExpenseService {
    @Autowired
    private final ReleaseExpenseRepository repository;

    public List<ReleaseExpense> fetchByRelease(Release release){
        log.info("Fetching Release Expenses for Release with id: {}", release.getId());
        return repository.findAllByRelease(release);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ReleaseExpense save(ReleaseExpense expense){
        log.info("Saving Release Expense for Release with id: {}", expense.getRelease().getId());
        return repository.save(expense);
    }

}

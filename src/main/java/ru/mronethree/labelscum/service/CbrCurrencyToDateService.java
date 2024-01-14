package ru.mronethree.labelscum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.mronethree.labelscum.domain.Curr;
import ru.mronethree.labelscum.domain.CurrencyToDate;
import ru.mronethree.labelscum.repository.CurrencyToDateRepository;
import ru.mronethree.labelscum.utils.XmlParser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Kirill Popov
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CbrCurrencyToDateService {
    @Autowired
    private final RestTemplate restTemplate;
    @Autowired
    private final XmlParser parser;
    @Autowired
    private final CurrencyToDateRepository repository;
    @Autowired
    private final ApplicationContext context;
    @Value("${labelscum.cbr-api.currency-to-date.url}")
    private String url;

    public List<CurrencyToDate> fetchByDate(LocalDate date){
        log.info("Fetching currencies rates from repository dated: {}", date);
        return repository.findCurrencyToDateByDate(date);
    }

    public boolean update(LocalDate date){
        Set<Curr> currList = fetchByDate(date).stream().map(CurrencyToDate::getCurrency).collect(Collectors.toSet());
//TODO: BTC should be checked separately
        List<Curr> noCrypto = Arrays.stream(Curr.values()).filter(c -> c != Curr.BTC).toList();
        if (!currList.containsAll(List.of(noCrypto))) {
            var self = context.getBean(CbrCurrencyToDateService.class);
            var result = fetchAPICurrenciesToDate(date);
            result.forEach(self::save);
        }
        return true;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CurrencyToDate save(CurrencyToDate currencyToDate){
        log.info("Saving Currency: {} to Date: {}", currencyToDate.getCurrency(),currencyToDate.getDate());
        if (repository.existsByCurrencyAndDate(currencyToDate.getCurrency(), currencyToDate.getDate())){
            return null;
        }
        return repository.save(currencyToDate);
    }
    private List<CurrencyToDate> fetchAPICurrenciesToDate(LocalDate date){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = date.format(pattern);
        log.info("Requesting third-party API for currencies rates at: {}", formattedDate);
        String response = restTemplate.getForObject(url + formattedDate, String.class);
        if (null == response || response.isEmpty()){
            throw new RuntimeException("Third party API service is not available!");
        }
        StringBuilder sb = new StringBuilder(response);
        sb.insert(sb.indexOf("<Valute"), "<Valutes>");
        sb.insert(sb.indexOf("</ValCurs>"), "</Valutes>");
        return parser.parseCurrencies(sb.toString(), date);
    }
}

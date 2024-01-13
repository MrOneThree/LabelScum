package ru.mronethree.labelscum.utils;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mronethree.labelscum.domain.Artist;
import ru.mronethree.labelscum.domain.Curr;
import ru.mronethree.labelscum.domain.DistributorReport;
import ru.mronethree.labelscum.domain.MusicDistributor;
import ru.mronethree.labelscum.service.ArtistService;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kirill Popov
 */
@Component
@RequiredArgsConstructor
public class CsvParsingUtils {
    @Autowired
    private final ArtistService artistService;

    private static BigDecimal getAmount(String stringAmount) {
        int del = stringAmount.indexOf(',');
        String intPart = stringAmount.strip().substring(0, del - 1);
        String decPart = stringAmount.strip().substring(del);
        BigDecimal result = BigDecimal.valueOf(Integer.parseInt(intPart));
        BigDecimal decimal = BigDecimal.valueOf(Integer.parseInt(decPart)).divide(BigDecimal.valueOf(100L));
        return result.add(decimal);
    }

    public List<DistributorReport> parseDistributorReport(InputStream inputStream, MusicDistributor distributor, LocalDate date, Curr currency){
        List<DistributorReport> reports = new ArrayList<>();
        InputStreamReader csvFileReader = new InputStreamReader(inputStream);
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        try(CSVReader reader = new CSVReaderBuilder(csvFileReader).withCSVParser(parser).build()) {
            List<String[]> entries = reader.readAll();
            for (int i = 1; i < entries.size(); i++){
                Artist artist = artistService.findByAlias(entries.get(i)[0]);
                BigDecimal amount = getAmount(entries.get(i)[1]);
                reports.add(DistributorReport.builder()
                        .date(date)
                        .musicDistributor(distributor)
                        .artist(artist)
                        .amount(amount)
                        .currency(currency)
                        .build()
                );

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return reports;
    }
}
